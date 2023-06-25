package com.example.happyplaces_repeat.domain

class GetHappyPlaceUseCase(private val repository: Repository) {

    suspend fun getHappyPlace(placeId: Int): HappyPlace{
        return repository.getHappyPlace(placeId)
    }
}