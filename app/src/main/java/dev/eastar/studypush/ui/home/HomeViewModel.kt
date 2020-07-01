package dev.eastar.studypush.ui.home

import android.log.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.eastar.studypush.data.StudyRepository
import kotlinx.coroutines.launch

//class HomeViewModel constructor(val studtData : StudyRepository): ViewModel() {
//class HomeViewModel @ViewModelInject constructor(private val repository: StudyRepository,
//                                                 @Assisted private val savedStateHandle: SavedStateHandle
//) : ViewModel() {

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun onLoad() {
        viewModelScope.launch {
            Log.e()
            //repository.getStudyList()
        }
    }
}