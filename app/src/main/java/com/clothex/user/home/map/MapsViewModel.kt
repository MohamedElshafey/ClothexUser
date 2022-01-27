package com.clothex.user.home.map

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.database.GetSavedLocationsUseCase
import com.clothex.data.domain.usecases.database.SaveLocationsUseCase
import com.clothex.data.local.room.entity.SavedLocation
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
) :
    ViewModel() {

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

}