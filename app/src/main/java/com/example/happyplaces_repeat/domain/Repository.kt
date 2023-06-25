package com.example.happyplaces_repeat.domain

import androidx.lifecycle.LiveData

interface Repository {
    suspend fun getHappyPlace(placeId: Int): HappyPlace

    fun getHappyPlacesList(): LiveData<List<HappyPlace>>

    suspend fun addHappyPlace(place: HappyPlace)

    suspend fun editHappyPlace(place: HappyPlace)

    suspend fun deleteHappyPlace(place: HappyPlace)

}