package com.example.happyplaces_repeat.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "HappyPlace_table")
data class HappyPlaceDbModel (

    val title: String,
    val description: String,
    val image: String,
    val date: String,
    val location: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
        )


