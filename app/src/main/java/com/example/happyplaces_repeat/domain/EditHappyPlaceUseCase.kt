package com.example.happyplaces_repeat.domain

class EditHappyPlaceUseCase(private val repository: Repository) {
    suspend fun editHappyPlace(place: HappyPlace){
        repository.editHappyPlace(place)
    }
}