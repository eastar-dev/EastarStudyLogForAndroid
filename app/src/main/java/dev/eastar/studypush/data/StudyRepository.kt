package dev.eastar.studypush.data

class StudyRepository(private val dataSource: StudyDataSource) {
    fun getStudyList(millisecond: Long = System.currentTimeMillis()) {
        dataSource.getStudyItems(3, millisecond)
    }
}