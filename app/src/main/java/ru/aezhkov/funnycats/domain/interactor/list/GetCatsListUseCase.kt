package ru.aezhkov.funnycats.domain.interactor.list

import io.reactivex.Single
import ru.aezhkov.funnycats.data.list.model.CatsModel

interface GetCatsListUseCase {
    fun getCats(page:Int):Single<List<CatsModel>>
}