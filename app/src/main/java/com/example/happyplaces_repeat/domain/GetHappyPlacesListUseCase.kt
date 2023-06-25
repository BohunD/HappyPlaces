package com.example.happyplaces_repeat.domain

import androidx.lifecycle.LiveData

class GetHappyPlacesListUseCase(private val repository: Repository) {
    fun getHappyPlacesList(): LiveData<List<HappyPlace>>{
        return repository.getHappyPlacesList()
    }
}