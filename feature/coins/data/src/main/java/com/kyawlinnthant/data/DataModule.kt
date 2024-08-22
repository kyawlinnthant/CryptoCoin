package com.kyawlinnthant.data

import com.kyawlinnthant.data.service.CoinsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): CoinsApi = retrofit.create(CoinsApi::class.java)
}
