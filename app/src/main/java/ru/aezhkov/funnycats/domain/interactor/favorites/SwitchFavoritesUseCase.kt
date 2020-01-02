package ru.aezhkov.funnycats.domain.interactor.favorites

import io.reactivex.Completable

interface SwitchFavoritesUseCase {

    fun switchFavorites(id: String):Completable
}