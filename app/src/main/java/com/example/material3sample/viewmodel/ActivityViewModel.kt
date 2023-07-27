package com.example.material3sample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {
    private val _useDynamicColor = MutableLiveData(false)
    val useDynamicColor: LiveData<Boolean> = _useDynamicColor

    private val _useDiffColorPalette = MutableLiveData(false)
    val useDiffPalette: LiveData<Boolean> = _useDiffColorPalette

    private val _useVerticalNav = MutableLiveData(false)
    val useVerticalNav: LiveData<Boolean> = _useVerticalNav

    fun updateDynamicColor(useDynamicColor: Boolean) {
        _useDynamicColor.value = useDynamicColor
    }

    fun updateOtherPalette(useOtherPalette: Boolean) {
        _useDiffColorPalette.value = useOtherPalette
    }

    fun useVerticalNav(useVertical: Boolean) {
        _useVerticalNav.value = useVertical
    }
}
