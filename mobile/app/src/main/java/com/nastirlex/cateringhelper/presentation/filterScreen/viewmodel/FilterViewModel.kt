package com.nastirlex.cateringhelper.presentation.filterScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cateringhelper.data.encryptedSharedPrefs.EncryptedStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel @Inject constructor(
    private val encryptedStorage: EncryptedStorage
) : ViewModel() {

    fun saveRating(rating: Int) = viewModelScope.launch(Dispatchers.IO) {
        encryptedStorage.saveRating(rating)
    }

    fun savePriceRange(minPrice: Int, maxPrice: Int) = viewModelScope.launch(Dispatchers.IO) {
        encryptedStorage.saveMinPrice(minPrice)
        encryptedStorage.saveMaxPrice(maxPrice)
    }

    fun saveLabel(label: String) = viewModelScope.launch(Dispatchers.IO) {
        encryptedStorage.saveLabel(label)
    }
}