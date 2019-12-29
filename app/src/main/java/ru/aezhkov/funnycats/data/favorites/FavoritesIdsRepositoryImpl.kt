package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class FavoritesIdsRepositoryImpl
@Inject constructor() : FavoritesIdsRepository {

    override fun getFavoritesCatsIds(): Single<List<String>> {
        return Single.just(listOf("123"))
    }

    override fun observeFavoritesChange(): Observable<String> {
        return Observable.just("123")
    }
}