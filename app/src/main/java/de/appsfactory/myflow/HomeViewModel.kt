package de.appsfactory.myflow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val hotData: LiveData<Int> = Model.sharedFlow.onEach {
        Log.d("MY_FLOW", "hot flow on each: $it")
    }.asLiveData()

    val coldData: LiveData<Int> = Model.simpleFlow.onEach {
        Log.d("MY_FLOW", "cold flow on each: $it")
    }.asLiveData()

    fun onCounter() {
        Model.onCounter()
    }
}