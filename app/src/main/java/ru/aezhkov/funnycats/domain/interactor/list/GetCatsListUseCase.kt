package ru.aezhkov.funnycats.domain.interactor.list

import io.reactivex.Observable
import ru.aezhkov.funnycats.data.list.model.CatsModel

interface GetCatsListUseCase {

    fun observeCats(): Observable<List<CatsModel>>

    fun loadFirstPage()

    fun loadNextPage()

    fun getCurrentList(): List<CatsModel>?
}