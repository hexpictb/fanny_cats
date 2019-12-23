package ru.aezhkov.funnycats.domain

import dagger.Binds
import dagger.Module
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListInteractor
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListUseCase

@Module
interface DomainLayerBinder {

    @Binds
    fun provideGetCatsListUseCase(interactor: GetCatsListInteractor): GetCatsListUseCase
}