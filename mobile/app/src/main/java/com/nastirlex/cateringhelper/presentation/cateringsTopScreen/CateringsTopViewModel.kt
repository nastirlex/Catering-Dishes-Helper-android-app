package com.nastirlex.cateringhelper.presentation.cateringsTopScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.PyObject
import com.google.gson.Gson
import com.nastirlex.cateringhelper.network.BranchGeneralDtoItem
import com.nastirlex.cateringhelper.network.EncryptedStorage
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
        var cateringsTopList: MutableList<BranchGeneralDtoItem> =
            gson.fromJson(result.toString(), Array<BranchGeneralDtoItem>::class.java)
                .toMutableList()
        Log.d("non filter", cateringsTopList.toString())

        // фильтрация по рейтингу и среднему чеку


        var newList = mutableListOf<BranchGeneralDtoItem>()

        for (branch in cateringsTopList) {
            val cuisines = branch.cuisines.split(", ")
            Log.d("branch cuisines", cuisines.toString())

            for (cuisine in userCuisines) {
                Log.d("cur cuisine", cuisine)
                if (!cuisines.contains(cuisine)) {
                    Log.d("dont contains", branch.toString() + cuisine)
                } else {
                    if (!newList.contains(branch))
                        newList.add(branch)
                }
            }
        }

        newList =
            newList.filter { it.general_rating >= userRating.toDouble() && it.avg_price >= userMinPrice && it.avg_price <= userMaxPrice }
                .toMutableList()

        _cateringsTop.postValue(newList)

    }
}