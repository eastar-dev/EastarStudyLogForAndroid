package dev.eastar.studypush.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.eastar.studypush.data.StudyRepository

class HomeViewModel constructor(val studtData : StudyRepository): ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


}