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

    private val studyItems = MutableLiveData<StudyItemList?>()
    val data: LiveData<StudyItemList?> = studyItems

    //val Flow<StudyItem>

    init {
        loadStudyList()
    }

    private fun loadStudyList() {
        viewModelScope.launch {
            val studyList: StudyItemList? = kotlin.runCatching {
                repository.getStudyList()
            }.onFailure {
                Log.printStackTrace(it)
                FirebaseCrashlytics.getInstance().log(it.toString())
                FirebaseCrashlytics.getInstance().recordException(it)
            }.getOrNull()


            //val studyList: StudyItemList? = repository.getStudyList()
            Log.e(studyList)
            studyItems.postValue(studyList)
        }
    }
}