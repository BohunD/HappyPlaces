package com.example.happyplaces_repeat.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.happyplaces_repeat.data.RepositoryImpl
import com.example.happyplaces_repeat.domain.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HappyPlaceViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RepositoryImpl(application)

    private val getHappyPlaceUseCase = GetHappyPlaceUseCase(repository)
    private val editHappyPlaceUseCase = EditHappyPlaceUseCase(repository)
    private val addHappyPlaceUseCase = AddHappyPlaceUseCase(repository)

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _errorInputTitle = MutableLiveData<Boolean>()
    val errorInputTitle: LiveData<Boolean>
        get() = _errorInputTitle

    private val _errorInputDescription = MutableLiveData<Boolean>()
    val errorInputDescription: LiveData<Boolean>
        get() = _errorInputDescription

    private val _errorInputDate = MutableLiveData<Boolean>()
    val errorInputDate: LiveData<Boolean>
        get() = _errorInputDate

    private val _errorInputLocation = MutableLiveData<Boolean>()
    val errorInputLocation: LiveData<Boolean>
        get() = _errorInputLocation

    private val _happyPlace = MutableLiveData<HappyPlace>()
    val happyPlace : LiveData<HappyPlace>
        get() =  _happyPlace

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen : LiveData<Unit>
        get() = _shouldCloseScreen

    fun addHappyPlace(title: String, desc: String,image: String, date: String, location: String){
        val title = parseText(title)
        val desc = parseText(desc)
        val date = parseText(date)
        val location = parseText(location)
        if(validateInput(title, desc, date, location)){
            scope.launch {
                val place = HappyPlace(title, desc,image ,date, location)
                addHappyPlaceUseCase.addHappyPlace(place)
                finishWork()
            }

        }
    }

    fun getHappyPlace(placeId: Int){
        scope.launch {
            val place = getHappyPlaceUseCase.getHappyPlace(placeId)
            _happyPlace.value = place
        }
    }

    fun editHappyPlace(title: String, desc: String,image: String, date: String, location: String){
        val title = parseText(title)
        val desc = parseText(desc)
        val date = parseText(date)
        val location = parseText(location)
        if(validateInput(title, desc, date, location)){
            _happyPlace.value?.let {
                scope.launch {
                    val place = it.copy(title = title, description =  desc, image =  image,
                        date = date,location= location)
                    editHappyPlaceUseCase.editHappyPlace(place)
                    finishWork()
                }
            }
        }
    }

    fun resetErrorTitle(){
        _errorInputTitle.value = false
    }

    fun resetErrorDescription(){
        _errorInputDescription.value = false
    }

    fun resetErrorDate(){
        _errorInputDate.value = false
    }

    fun resetErrorLocation(){
        _errorInputLocation.value = false
    }

    private fun parseText(text: String?):String{
        return text?.trim() ?: ""
    }

    private fun validateInput(title: String, description: String, date: String, location: String): Boolean{
        var result = true
        if(title.isBlank()){
            _errorInputTitle.value = true
            result = false
        }
        if(description.isBlank()){
            _errorInputDescription.value = true
            result = false
        }
        if(date.isBlank()){
            _errorInputDate.value = true
            result = false
        }
        if(location.isBlank()){
            _errorInputLocation.value = true
            result = false
        }
        return result
    }

    private fun finishWork(){
        _shouldCloseScreen.value = Unit
    }

}