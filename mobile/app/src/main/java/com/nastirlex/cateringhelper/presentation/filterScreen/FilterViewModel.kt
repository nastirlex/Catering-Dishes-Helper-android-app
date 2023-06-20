package com.nastirlex.cateringhelper.presentation.filterScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nastirlex.cateringhelper.network.EncryptedStorage
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
}