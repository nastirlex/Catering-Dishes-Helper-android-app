package com.nastirlex.cateringhelper.presentation.dishesScreen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.PyObject
import com.google.gson.Gson
import com.nastirlex.cateringhelper.data.dto.DishDto
import com.nastirlex.cateringhelper.data.encryptedSharedPrefs.EncryptedStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Math.floor
import javax.inject.Inject

@HiltViewModel
class DishesViewModel @Inject constructor(
    private val python: PyObject,
    encryptedStorage: EncryptedStorage
) : ViewModel() {

    private val _dishes = MutableLiveData<MutableList<DishDto>>()
    val dishes: LiveData<MutableList<DishDto>>
        get() = _dishes

    private val label = encryptedStorage.getLabel()
    private val unwantedIngredients = encryptedStorage.getUnwantedIngredients().split(", ")

    fun getCateringDishes(cateringId: String) = viewModelScope.launch(Dispatchers.IO) {
        val recommendFunc = python["getCatter_dishes"]
        val result = recommendFunc?.call()

        val gson = Gson()
        val dishesList: MutableList<DishDto> =
            gson.fromJson(result.toString(), Array<DishDto>::class.java)
                .toMutableList()


        val newDishesList = mutableListOf<DishDto>()

        // больше 0.45 - есть ингредиент, меньше - нет

        // фильтрация по id заведения и категории блюда
        loop@
        for (dish in dishesList) {
            if (dish.id.toString() == cateringId) {
                if (dish.label == label) {
                    // проверяем на наличие нежелательных ингредиентов

                    var isForbidden = false
                    loop2@
                    for (ingredient in unwantedIngredients) {
                        var coeff = 0.00
                        when (ingredient) {
                            "Овощи" -> {
                                coeff = kotlin.math.floor(dish.vegetables * 100.0) / 100.0
                            }

                            "Морепродукты" -> {
                                coeff = kotlin.math.floor(dish.seafood * 100.0) / 100.0
                            }

                            "Фрукты" -> {
                                coeff = kotlin.math.floor(dish.fruits * 100.0) / 100.0
                            }

                            "Грибы" -> {
                                coeff = kotlin.math.floor(dish.mushrooms * 100.0) / 100.0
                            }

                            "Злаковые" -> {
                                coeff = kotlin.math.floor(dish.grains * 100.0) / 100.0
                            }
                        }

                        Log.d("коеф", coeff.toString())

                        if (coeff > 0.48) {
                            isForbidden = true
                        } else {
                            continue@loop2
                        }
                    }


                    if (!isForbidden)
                        newDishesList.add(dish)
                }
            }
        }

        Log.d("dishes", dishesList.toString())

        _dishes.postValue(newDishesList)
    }
}