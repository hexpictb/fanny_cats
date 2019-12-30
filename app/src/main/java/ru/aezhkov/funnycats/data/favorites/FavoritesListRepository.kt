package ru.aezhkov.funnycats.data.favorites

import io.reactivex.Single
import ru.aezhkov.funnycats.data.list.model.CatsModel

interface FavoritesListRepository {

    fun getFavoritesCatsList(): Single<List<CatsModel>>
}