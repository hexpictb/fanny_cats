package ru.aezhkov.funnycats.domain

import dagger.Binds
import dagger.Module
import ru.aezhkov.funnycats.domain.interactor.favorites.FavoritesInteractor
import ru.aezhkov.funnycats.domain.interactor.favorites.FavoritesListInteractor
import ru.aezhkov.funnycats.domain.interactor.favorites.FavoritesListUseCase
import ru.aezhkov.funnycats.domain.interactor.favorites.SwitchFavoritesUseCase
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListInteractor
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListUseCase

@Module
interface DomainLayerBinder {

    @Binds
    fun provideGetCatsListUseCase(interactor: GetCatsListInteractor): GetCatsListUseCase

    @Binds
    fun provideSwitchFavoritesUseCase(interactor: FavoritesInteractor): SwitchFavoritesUseCase

    @Binds
    fun provideFavoritesListUseCase(interactor: FavoritesListInteractor): FavoritesListUseCase
}