package com.example.easycareproject.ui.theme.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DailyTipsViewModel : ViewModel() {
    private val _goals = MutableLiveData(listOf(false, false, false))
    val goals: LiveData<List<Boolean>> get() = _goals

    private val _goalDescriptions = MutableLiveData(listOf("", "", ""))
    val goalDescriptions: LiveData<List<String>> get() = _goalDescriptions

    private val _progress = MutableLiveData(0)
    val progress: LiveData<Int> get() = _progress

    init {
        calculateProgress()
    }

    fun updateGoal(index: Int, isChecked: Boolean) {
        _goals.value = _goals.value?.toMutableList()?.apply { this[index] = isChecked }
        calculateProgress()
        Log.d("DailyTipsViewModel", "Updated goals: ${_goals.value}")
    }

    fun updateGoalDescription(index: Int, description: String) {
        _goalDescriptions.value = _goalDescriptions.value?.toMutableList()?.apply { this[index] = description }
        Log.d("DailyTipsViewModel", "Updated goal description at index $index: $description")
    }

    fun calculateProgress() {
        _goals.value?.let { goals ->
            val completed = goals.count { it }
            val percentage = (completed * 100) / goals.size
            _progress.value = percentage
        }
    }

    fun refreshGoals() {
        _goals.value = listOf(false, false, false)
        _goalDescriptions.value = listOf("", "", "")
        calculateProgress()
        Log.d("DailyTipsViewModel", "Goals and descriptions refreshed.")
    }

    fun updateProgress() {
        _goals.value?.let { goals ->
            val completedGoals = goals.count { it }
            val newProgress = (completedGoals * 100) / goals.size
            _progress.value = newProgress
            Log.d("DailyTipsViewModel", "Updated progress: $newProgress%")
        }
    }
}
