package com.example.happyplaces_repeat.data.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.happyplaces_repeat.domain.HappyPlace

interface HappyPlaceDao {
    @Query("SELECT * FROM HappyPlace_table")
    fun getHappyPlacesList(): LiveData<List<HappyPlaceDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHappyPlace(place: HappyPlaceDbModel)

    @Query ("DELETE FROM HappyPlace_table WHERE id=:placeId")
    suspend fun deleteHappyPlace(placeId: Int)

    @Query ("SELECT * FROM HappyPlace_table WHERE id=:placeId")
    suspend fun getHappyPlace(placeId: Int): HappyPlaceDbModel
}