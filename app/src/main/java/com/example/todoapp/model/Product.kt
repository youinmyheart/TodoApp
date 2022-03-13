package com.example.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class Product(@PrimaryKey(autoGenerate = true) val id: Int,
                   @ColumnInfo val name: String,
                   @ColumnInfo val price: Int,
                   @ColumnInfo val quantity: Int,
                   @ColumnInfo val type: Int)