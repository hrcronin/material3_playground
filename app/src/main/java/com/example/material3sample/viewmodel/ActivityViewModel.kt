package com.example.material3sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel: ViewModel() {
    private val _useDynamicColor = MutableLiveData(false)
    val useDynamicColor: LiveData<Boolean> = _useDynamicColor

    fun updateDynamicColor(useDynamicColor: Boolean) {
        _useDynamicColor.value = useDynamicColor
    }
}