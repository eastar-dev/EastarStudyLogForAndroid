package dev.eastar.studypush.main

import android.log.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.eastar.studypush.data.StudyRepository
import kotlinx.coroutines.launch

class AppMainViewModel @ViewModelInject constructor(private val repository: StudyRepository,
                                                    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is AppMain Activity"
    }
    val text: LiveData<String> = _text

    fun onLoad() {
        viewModelScope.launch {
            Log.e()
            repository.getStudyList()
        }
    }
}