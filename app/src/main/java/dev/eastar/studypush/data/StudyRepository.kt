package dev.eastar.studypush.data

import android.log.Log
import dev.eastar.studypush.data.model.StudyItemList

class StudyRepository(private val dataSource: StudyDataSource) {
    suspend fun getStudyList(): StudyItemList {
        Log.e(dataSource)
        return dataSource.getStudyItems(3, System.currentTimeMillis())
    }
}