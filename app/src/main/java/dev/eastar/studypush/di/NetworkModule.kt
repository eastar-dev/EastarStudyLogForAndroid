package dev.eastar.studypush.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import smart.base.NN
import smart.net.NetCookie
import smart.net.okHttpClientBuilder
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    private const val CONNECT_TIMEOUT: Long = 10                // ConnectTimeout Default 180
    private const val WRITE_TIMEOUT: Long = 300                 // WriteTimeout Default 180
    private const val READ_TIMEOUT: Long = 300                  // ReadTimeout Default 180

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return okHttpClientBuilder().apply {
            connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            cookieJar(NetCookie())
        }.build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl(NN.HOST) // BaseUrl 설정
            addConverterFactory(GsonConverterFactory.create())         // Respone Gson
            addConverterFactory(ScalarsConverterFactory.create())      // Respone String
            client(okHttpClient)
        }.build()
    }
}