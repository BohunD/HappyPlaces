package com.example.happyplaces_repeat.data.database

import com.example.happyplaces_repeat.domain.HappyPlace

class HappyPlaceMapper {
    fun mapEntityToDbModel(place:HappyPlace) = HappyPlaceDbModel(
        id = place.id,
        title = place.title,
        description = place.description,
        image = place.image,
        location = place.location,
        date = place.date
    )
    fun mapDbModelToEntity(place:HappyPlaceDbModel) = HappyPlace(
        id = place.id,
        title = place.title,
        description = place.description,
        image = place.image,
        location = place.location,
        date = place.date
    )
    fun mapListDbModelToListEntity(list: List<HappyPlaceDbModel>) = list.map{
        mapDbModelToEntity(it)
    }
}