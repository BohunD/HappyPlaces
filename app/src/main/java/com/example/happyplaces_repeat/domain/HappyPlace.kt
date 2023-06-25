package com.example.happyplaces_repeat.domain

data class HappyPlace (

    val title: String,
    val description: String,
    val image: String,
    val date: String,
    val location: String,
    val id: Int = UNDEFINED_ID
        ){
    companion object{
        const val UNDEFINED_ID = 0
    }
}