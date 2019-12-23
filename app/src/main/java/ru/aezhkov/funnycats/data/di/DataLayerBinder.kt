package ru.aezhkov.funnycats.data.di

import dagger.Binds
import dagger.Module
import ru.aezhkov.funnycats.data.list.repository.CatsListRepository
import ru.aezhkov.funnycats.data.list.repository.CatsListRepositoryImpl

@Module
interface DataLayerBinder {

    @Binds
    fun provideCatsListRepository(impl: CatsListRepositoryImpl): CatsListRepository
}