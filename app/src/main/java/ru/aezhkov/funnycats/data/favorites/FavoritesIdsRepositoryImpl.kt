package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import ru.aezhkov.funnycats.data.list.model.CatsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesIdsRepositoryImpl
@Inject constructor() : FavoritesIdsRepository {

    private val favoritesChangeSubject = PublishSubject.create<Pair<String, Boolean>>()

    override fun observeFavoritesChange(): Observable<Pair<String, Boolean>> = favoritesChangeSubject

    override fun addToFavorites(model: CatsModel) {
        favoritesChangeSubject.onNext(Pair(model.id, true))
    }

    override fun removeFromFavorites(model: CatsModel) {
        favoritesChangeSubject.onNext(Pair(model.id, false))
    }

    override fun getFavoritesCatsIds(): Single<List<String>> {
        return Single.just(listOf("123"))
    }
}