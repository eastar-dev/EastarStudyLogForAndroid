package dev.eastar.studypush.data

import smart.net.Net

class StudyRepository(val dataSource: StudyDataSource = Net.create()) {
    fun getStudyList(millisecond: Long = System.currentTimeMillis()) {
        dataSource.getStudyItems(3, millisecond)
    }
}