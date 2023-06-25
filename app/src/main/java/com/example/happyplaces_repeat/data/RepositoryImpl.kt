package com.example.happyplaces_repeat.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.happyplaces_repeat.data.database.AppDatabase
import com.example.happyplaces_repeat.data.database.HappyPlaceDao
import com.example.happyplaces_repeat.data.database.HappyPlaceMapper
import com.example.happyplaces_repeat.domain.HappyPlace
import com.example.happyplaces_repeat.domain.Repository

class RepositoryImpl(application: Application): Repository {

    private val happyPlaceDao = AppDatabase.getInstance(application).happyPlacesDao()
    private val mapper = HappyPlaceMapper()


    override suspend fun getHappyPlace(placeId: Int): HappyPlace {
        val dbModel = happyPlaceDao.getHappyPlace(placeId)
        return mapper.mapDbModelToEntity(dbModel)
    }

    override fun getHappyPlacesList(): LiveData<List<HappyPlace>> = Transformations
        .map(happyPlaceDao.getHappyPlacesList()){
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun addHappyPlace(place: HappyPlace) {
        happyPlaceDao.addHappyPlace(mapper.mapEntityToDbModel(place))
    }

    override suspend fun editHappyPlace(place: HappyPlace) {
        happyPlaceDao.addHappyPlace(mapper.mapEntityToDbModel(place))
    }

    override suspend fun deleteHappyPlace(place: HappyPlace) {
        happyPlaceDao.deleteHappyPlace(place.id)
    }

}