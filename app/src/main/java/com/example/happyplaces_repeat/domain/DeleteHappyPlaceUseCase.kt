package com.example.happyplaces_repeat.domain

class DeleteHappyPlaceUseCase(private val repository: Repository) {
    suspend fun deleteHappyPlace(place: HappyPlace){
        repository.deleteHappyPlace(place)
    }
}