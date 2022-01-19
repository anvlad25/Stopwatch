package com.example.stopwatch.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stopwatch.model.*

class MainViewModel(stopwatchListOrchestrator: StopwatchListOrchestrator) : ViewModel() {
    private val _liveData = MutableLiveData<StopwatchListOrchestrator>()
    val liveData: MutableLiveData<StopwatchListOrchestrator> get() = _liveData

    init {
        _liveData.postValue(stopwatchListOrchestrator)
    }
}