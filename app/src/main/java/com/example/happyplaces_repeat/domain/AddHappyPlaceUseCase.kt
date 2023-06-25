package com.example.happyplaces_repeat.domain

class AddHappyPlaceUseCase(private val repository: Repository) {
    suspend fun addHappyPlace(place: HappyPlace){
        repository.addHappyPlace(place)
    }
}