package ru.aezhkov.funnycats.domain.interactor.list

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ru.aezhkov.funnycats.data.list.model.CatsModel
import ru.aezhkov.funnycats.data.list.repository.CatsListRepository
import javax.inject.Inject

class GetCatsListInteractor
@Inject constructor(
    private val repository: CatsListRepository
) : GetCatsListUseCase {

    override fun getCats(page: Int): Single<List<CatsModel>> {
        return repository.getCatsList(page)
            .subscribeOn(Schedulers.io())
    }
}