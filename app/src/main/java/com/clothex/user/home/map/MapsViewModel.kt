package com.clothex.user.home.map

import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.database.GetSavedLocationsUseCase
import com.clothex.data.domain.usecases.database.SaveLocationsUseCase
import com.clothex.data.local.room.entity.SavedLocation
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class MapsViewModel(
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    private val saveLocationsUseCase: SaveLocationsUseCase
) : BaseLanguageViewModel() {

    val locationListLiveData = MutableLiveData<List<SavedLocation>>()

    fun fetchLocations() {
        viewModelScope.launch {
            getSavedLocationsUseCase.invoke(Unit).collect {
                withContext(Dispatchers.IO) { locationListLiveData.postValue(it) }
            }
        }
    }

    fun saveLocation(savedLocation: SavedLocation, callback: (Boolean) -> Unit) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                callback(saveLocationsUseCase.invoke(savedLocation))
            }
        }
    }

    val searchResultLiveData = MutableLiveData<List<Address>>()

    fun geocode(key: String, geocoder: Geocoder) {
        searchResultLiveData.postValue(
            try {
                geocoder.getFromLocationName(key, 1, 22.115144, 25.029469, 31.180246, 32.357939)
            } catch (exception: Exception) {
                emptyList()
            }
        )
    }

}