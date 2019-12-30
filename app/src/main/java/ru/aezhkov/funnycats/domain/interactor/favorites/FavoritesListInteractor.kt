package ru.aezhkov.funnycats.domain.interactor.favorites

import io.reactivex.Single
import ru.aezhkov.funnycats.data.list.model.CatsModel
import javax.inject.Inject

class FavoritesListInteractor
@Inject constructor() : FavoritesListUseCase {

    override fun getFavoritesCatsList(): Single<List<CatsModel>> {
        return Single.just(
            listOf(
                CatsModel("12", "https://cdn2.thecatapi.com/images/2f5.jpg", true)
            )
        )
    }
}