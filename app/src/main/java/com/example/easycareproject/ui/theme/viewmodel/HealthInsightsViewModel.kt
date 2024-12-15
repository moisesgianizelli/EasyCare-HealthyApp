package com.example.easycareproject.ui.theme.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HealthInsightsViewModel : ViewModel() {
    private val _insightValue = MutableLiveData(72)
    val insightValue: LiveData<Int> get() = _insightValue

    fun updateInsightValue(newValue: Int) {
        _insightValue.value = newValue
    }
}