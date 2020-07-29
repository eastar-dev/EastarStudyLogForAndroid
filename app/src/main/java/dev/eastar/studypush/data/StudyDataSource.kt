package dev.eastar.studypush.data

import dev.eastar.studypush.data.model.StudyItemList
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */

interface StudyDataSource {
    @FormUrlEncoded
    @POST("http://192.168.1.6:18080/item")
    suspend fun getStudyItems(
        @Field("size") size: Int,
        @Field("millisecond") millisecond: Long = System.currentTimeMillis()
    ): StudyItemList
}
