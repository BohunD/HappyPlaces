package com.example.happyplaces_repeat.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.happyplaces_repeat.data.RepositoryImpl
import com.example.happyplaces_repeat.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val deleteHappyPlaceUseCase = DeleteHappyPlaceUseCase(repository)
    private val getHappyPlacesUseCase = GetHappyPlacesListUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.Default)

    val placesList = getHappyPlacesUseCase.getHappyPlacesList()

    private fun deleteHappyPlace(place: HappyPlace){
        scope.launch{
            deleteHappyPlaceUseCase.deleteHappyPlace(place)
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }

}