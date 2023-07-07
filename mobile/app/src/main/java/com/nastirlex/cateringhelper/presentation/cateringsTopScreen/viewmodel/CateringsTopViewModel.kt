package com.nastirlex.cateringhelper.presentation.cateringsTopScreen.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.PyObject
import com.google.gson.Gson
import com.nastirlex.cateringhelper.data.dto.BranchGeneralDtoItem
import com.nastirlex.cateringhelper.data.encryptedSharedPrefs.EncryptedStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CateringsTopViewModel @Inject constructor(
    private val python: PyObject,
    encryptedStorage: EncryptedStorage
) : ViewModel() {

    private val _cateringsTop = MutableLiveData<MutableList<BranchGeneralDtoItem>>()
    val cateringsTop: LiveData<MutableList<BranchGeneralDtoItem>>
        get() = _cateringsTop

    private val userRating = encryptedStorage.getRating()
    private val userMinPrice = encryptedStorage.getMinPrice()
    private val userMaxPrice = encryptedStorage.getMaxPrice()
    private val userCuisines = encryptedStorage.getCuisines().split(", ")


    fun getTopThree(cateringName: String) = viewModelScope.launch(Dispatchers.IO) {
        val recommendFunc = python["recommend"]
        val result = recommendFunc?.call(cateringName)

        val gson = Gson()
        val cateringsTopList: MutableList<BranchGeneralDtoItem> =
            gson.fromJson(result.toString(), Array<BranchGeneralDtoItem>::class.java)
                .toMutableList()

        Log.d("catering top", cateringsTopList.toString())
        
        // фильтрация по рейтингу и среднему чеку, кухням

        var newList = mutableListOf<BranchGeneralDtoItem>()

        for (branch in cateringsTopList) {
            val cuisines = branch.cuisines.split(", ")

            for (cuisine in userCuisines) {
                if (cuisines.contains(cuisine)) {
                    newList.add(branch)
                    break
                }
            }
        }

        newList =
            newList.filter { it.general_rating >= userRating.toDouble() && it.avg_price >= userMinPrice && it.avg_price <= userMaxPrice }
                .toMutableList()

        _cateringsTop.postValue(newList)

    }
}