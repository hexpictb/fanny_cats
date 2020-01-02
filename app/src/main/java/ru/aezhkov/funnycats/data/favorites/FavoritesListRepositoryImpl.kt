package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Single
import ru.aezhkov.funnycats.data.favorites.dao.FavoritesCatsDao
import ru.aezhkov.funnycats.data.list.model.CatsModel
import javax.inject.Inject

class FavoritesListRepositoryImpl
@Inject constructor(
    private val dao: FavoritesCatsDao
) : FavoritesListRepository {

    override fun getFavoritesCatsList(): Single<List<CatsModel>> {
        return dao.getAll().map { list ->
            list.map { CatsModel(it.id, it.url, isFavorites = true) }
        }
    }
}