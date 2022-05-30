package com.clothex.user.profile.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.usecases.database.DeleteLocationUseCase
import com.clothex.data.domain.usecases.database.GetSavedLocationsUseCase
import com.clothex.data.local.room.entity.SavedLocation
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class EditLocationViewModel(
    private val getSavedLocationsUseCase: GetSavedLocationsUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase
) : BaseLanguageViewModel() {

    val locationListLiveData = MutableLiveData<List<SavedLocation>>()

    fun fetchLocations() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getSavedLocationsUseCase.invoke(Unit).collect {
                    locationListLiveData.postValue(it)
                }
            }
        }
    }

    fun deleteLocation(savedLocation: SavedLocation) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                deleteLocationUseCase.invoke(savedLocation)
                fetchLocations()
            }
        }
    }

}