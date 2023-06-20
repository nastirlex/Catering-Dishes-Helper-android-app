package com.nastirlex.cateringhelper.presentation.choiceCateringScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaquo.python.PyObject
import com.google.gson.Gson
import com.nastirlex.cateringhelper.network.BranchGeneralDtoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoiceCateringViewModel @Inject constructor(
    private val python: PyObject
) : ViewModel() {

    private val _caterings = MutableLiveData<List<BranchGeneralDtoItem>>()
    val caterings: LiveData<List<BranchGeneralDtoItem>>
        get() = _caterings

    init {
        getAllCatering()
    }

    fun getAllCatering() = viewModelScope.launch(Dispatchers.IO) {
        val getCateringFunc = python["getCater"]
        val result = getCateringFunc?.call()

        val gson = Gson()
        val cateringsList: List<BranchGeneralDtoItem> =
            gson.fromJson(result.toString(), Array<BranchGeneralDtoItem>::class.java).toList()

        _caterings.postValue(cateringsList)

        Log.d("result", cateringsList.size.toString())
    }
}