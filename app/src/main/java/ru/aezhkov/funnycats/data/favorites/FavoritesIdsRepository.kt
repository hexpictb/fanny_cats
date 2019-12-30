package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Observable
import io.reactivex.Single
import ru.aezhkov.funnycats.data.list.model.CatsModel

interface FavoritesIdsRepository {

    fun observeFavoritesChange(): Observable<Pair<String, Boolean>>

    fun addToFavorites(model: CatsModel)

    fun removeFromFavorites(model: CatsModel)

    fun getFavoritesCatsIds(): Single<List<String>>
}