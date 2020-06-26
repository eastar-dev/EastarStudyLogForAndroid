package dev.eastar.studypush.data

import dev.eastar.studypush.data.model.StudyItemList
import retrofit2.http.Field
import retrofit2.http.POST

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

interface StudyDataSource {
    @POST("item")
    fun getStudyItems(
        @Field("size") size: Int,
        @Field("millisecond") millisecond: Long = System.currentTimeMillis()
    ): StudyItemList
}
