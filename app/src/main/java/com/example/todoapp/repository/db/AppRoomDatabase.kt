package com.example.todoapp.repository.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Product::class], version = 1)
abstract class AppRoomDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private val TAG = AppRoomDatabase::class.java.simpleName

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "todoapp_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    .fallbackToDestructiveMigration()
                    .addCallback(TodoAppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class TodoAppDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d(TAG, "onCreate")
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        // add dummy data for testing
                        populateDatabase(database.productDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(productDao: ProductDao) {
            Log.d(TAG, "populateDatabase")
            productDao.deleteAll()

            var product = Product(1, "iPhone X", 150000, 1, 2)
            productDao.insert(product)
            product = Product(2, "TV", 38000, 2, 2)
            productDao.insert(product)
            product = Product(3, "Table", 12000, 1, 2)
            productDao.insert(product)
        }
    }
}