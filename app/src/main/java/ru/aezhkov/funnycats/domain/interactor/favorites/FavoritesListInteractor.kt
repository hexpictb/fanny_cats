package ru.aezhkov.funnycats.domain.interactor.favorites

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.aezhkov.funnycats.data.favorites.FavoritesListRepository
import ru.aezhkov.funnycats.data.list.model.CatsModel
import javax.inject.Inject

class FavoritesListInteractor
@Inject constructor(
    private val favoritesListRepository: FavoritesListRepository
) : FavoritesListUseCase {

    override fun getFavoritesCatsList(): Single<List<CatsModel>> {
        return favoritesListRepository.getFavoritesCatsList()
            .subscribeOn(Schedulers.io())
    }
}