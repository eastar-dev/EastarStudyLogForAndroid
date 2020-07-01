package dev.eastar.studypush.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.eastar.studypush.data.StudyDataSource
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object StudyDataSourceModule {
    @Provides
    fun provideStudyDataSource(retrofit: Retrofit): StudyDataSource {
        return retrofit.create(StudyDataSource::class.java)
    }

    //@Provides
    //fun provideStudyDataSource(@ApplicationContext application: Application, retrofit: Retrofit): StudyDataSource {
    //    return retrofit.create(StudyDataSource::class.java)
    //}
}