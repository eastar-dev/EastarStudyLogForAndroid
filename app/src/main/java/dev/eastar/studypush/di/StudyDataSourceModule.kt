package dev.eastar.studypush.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dev.eastar.studypush.data.StudyDataSource
import dev.eastar.studypush.data.StudyRepository
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
object StudyDataSourceModule {
    @Provides
    fun provideStudyDataSource(retrofit: Retrofit): StudyDataSource {
        return retrofit.create(StudyDataSource::class.java)
    }

    @Provides
    fun provideStudyRepository(source: StudyDataSource): StudyRepository {
        return StudyRepository(source)
    }
}