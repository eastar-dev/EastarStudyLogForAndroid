package dev.eastar.studypush.ui.home

import android.log.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dev.eastar.studypush.data.StudyRepository
import dev.eastar.studypush.data.model.StudyItemList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeViewModel @ViewModelInject constructor(
    private val repository: StudyRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    //private val _text = MutableLiveData<String>().apply { value = "This is home Fragment" }
    //val text: LiveData<String> = _text

    private val studyItems = MutableLiveData<StudyItemList?>()
    val data: LiveData<StudyItemList?> = studyItems

    init {
        loadStudyList()
    }

    fun loadStudyList() {
        viewModelScope.launch {
            val studyList: StudyItemList? = withContext(Dispatchers.IO) {
                kotlin.runCatching {
                    repository.getStudyList()
                }.onFailure {
                    FirebaseCrashlytics.getInstance().log(it.toString())
                    FirebaseCrashlytics.getInstance().recordException(it)
                }.getOrNull()
            }
            Log.e(studyList)
            studyItems.postValue(studyList)
        }
    }
}