package dev.eastar.studypush.ui.home

import android.log.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

//class HomeViewModel constructor(val studtData : StudyRepository): ViewModel() {
class HomeViewModel constructor() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun onLoad() {
        viewModelScope.launch {
            Log.e()
        }
    }


}