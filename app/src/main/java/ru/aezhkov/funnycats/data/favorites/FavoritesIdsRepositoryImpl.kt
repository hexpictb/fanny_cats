package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject
import ru.aezhkov.funnycats.data.favorites.dao.FavoritesCatsDao
import ru.aezhkov.funnycats.data.favorites.model.FavoriteCatEntity
import ru.aezhkov.funnycats.data.list.model.CatsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesIdsRepositoryImpl
@Inject constructor(
    private val dao: FavoritesCatsDao
) : FavoritesIdsRepository {

    private val favoritesChangeSubject = PublishSubject.create<Pair<String, Boolean>>()

    override fun observeFavoritesChange(): Observable<Pair<String, Boolean>> = favoritesChangeSubject

    override fun addToFavorites(model: CatsModel): Completable {
        favoritesChangeSubject.onNext(Pair(model.id, true))
        return dao.insert(FavoriteCatEntity(model.id, model.url))
    }

    override fun removeFromFavorites(model: CatsModel): Completable {
        favoritesChangeSubject.onNext(Pair(model.id, false))
        return dao.delete(FavoriteCatEntity(model.id, model.url))
    }

    override fun getFavoritesCatsIds(): Single<List<String>> = dao.getAll().map { list -> list.map { it.id } }
}