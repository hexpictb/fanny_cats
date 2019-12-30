package ru.aezhkov.funnycats.domain.interactor.favorites

import io.reactivex.Single
import ru.aezhkov.funnycats.data.list.model.CatsModel

interface FavoritesListUseCase {

    fun getFavoritesCatsList(): Single<List<CatsModel>>
}