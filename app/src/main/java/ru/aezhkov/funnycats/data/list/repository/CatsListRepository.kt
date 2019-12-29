package ru.aezhkov.funnycats.data.list.repository

import io.reactivex.Single
import ru.aezhkov.funnycats.data.list.model.CatsModel

interface CatsListRepository {

    fun getCatsList(page:Int): Single<List<CatsModel>>

}