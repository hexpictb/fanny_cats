package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Observable
import io.reactivex.Single

interface FavoritesIdsRepository {

    fun getFavoritesCatsIds(): Single<List<String>>

    fun observeFavoritesChange(): Observable<Pair<String, Boolean>>
}