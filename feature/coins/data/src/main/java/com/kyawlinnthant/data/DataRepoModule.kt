package com.kyawlinnthant.data

import com.kyawlinnthant.data.repository.CoinsRepository
import com.kyawlinnthant.data.repository.CoinsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataRepoModule {
    @Binds
    @Singleton
    fun bindRepo(repo: CoinsRepositoryImpl): CoinsRepository
}
