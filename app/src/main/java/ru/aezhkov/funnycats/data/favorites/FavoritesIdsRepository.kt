package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.aezhkov.funnycats.data.list.model.CatsModel

interface FavoritesIdsRepository {

    fun observeFavoritesChange(): Observable<Pair<String, Boolean>>

    fun addToFavorites(model: CatsModel): Completable

    fun removeFromFavorites(model: CatsModel): Completable

    fun getFavoritesCatsIds(): Single<List<String>>
}