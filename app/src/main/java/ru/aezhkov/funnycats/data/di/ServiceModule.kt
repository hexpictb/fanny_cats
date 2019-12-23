package ru.aezhkov.funnycats.data.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.aezhkov.funnycats.data.network.CatsService

@Module
class ServiceModule {

    @Provides
    fun provideCatsService(retrofit: Retrofit): CatsService {
        return retrofit.create(CatsService::class.java)
    }
}