package ru.aezhkov.funnycats.data.list.repository

import io.reactivex.Single
import ru.aezhkov.funnycats.data.favorites.FavoritesIdsRepository
import ru.aezhkov.funnycats.data.network.CatsService
import ru.aezhkov.funnycats.data.list.model.CatsModel
import javax.inject.Inject

class CatsListRepositoryImpl
@Inject constructor(
    private val service: CatsService,
    private val favoritesIdsRepository: FavoritesIdsRepository
) : CatsListRepository {

    override fun getCatsList(page: Int): Single<List<CatsModel>> {
        return service.getCats(page, limit = 20)
            .flatMap { response ->
                favoritesIdsRepository.getFavoritesCatsIds()
                    .map { favoritesIds ->
                        response.map {
                            val isFavorites = favoritesIds.contains(it.id)
                            CatsModel(it.id, it.url, isFavorites)
                        }
                    }
            }
    }
}