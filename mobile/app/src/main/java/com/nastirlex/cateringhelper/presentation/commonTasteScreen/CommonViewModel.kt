package com.nastirlex.cateringhelper.presentation.commonTasteScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.PyObject
import com.nastirlex.cateringhelper.network.EncryptedStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    private val encryptedStorage: EncryptedStorage
) : ViewModel() {

    fun saveIsVegetarian(isVegetarian: Boolean) = viewModelScope.launch(Dispatchers.IO) {
        encryptedStorage.saveIsVegetarian(isVegetarian)
    }

    fun saveCuisines(cuisines: String) = viewModelScope.launch(Dispatchers.IO) {
        encryptedStorage.saveCuisines(cuisines)
    }

    fun saveUnwantedIngredients(ingredients: String) = viewModelScope.launch(Dispatchers.IO) {
        encryptedStorage.saveUnwantedIngredients(ingredients)
    }

}