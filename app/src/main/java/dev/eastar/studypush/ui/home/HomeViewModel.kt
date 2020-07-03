package dev.eastar.studypush.ui.home

import android.log.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dev.eastar.studypush.data.StudyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel @ViewModelInject constructor(
    private val repository: StudyRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _text = MutableLiveData<String>().apply { value = "This is home Fragment" }
    val text: LiveData<String> = _text

    init {
        loadStudyList()
    }

    fun loadStudyList() {
        viewModelScope.launch {
            val studyList = withContext(Dispatchers.IO) { repository.getStudyList() }
            Log.e(studyList)
            _text.postValue(studyList.toString())
        }
    }
}