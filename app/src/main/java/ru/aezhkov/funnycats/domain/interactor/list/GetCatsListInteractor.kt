package ru.aezhkov.funnycats.domain.interactor.list

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.aezhkov.funnycats.data.favorites.FavoritesIdsRepository
import ru.aezhkov.funnycats.data.list.model.CatsModel
import ru.aezhkov.funnycats.data.list.repository.CatsListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCatsListInteractor
@Inject constructor(
    private val repository: CatsListRepository,
    private val favoritesIdsRepository: FavoritesIdsRepository
) : GetCatsListUseCase {

    private val catsLoadSubject = PublishSubject.create<Unit>()
    private var page = 0
    private val catsResultSubject = BehaviorSubject.create<List<CatsModel>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        unsubscribeOnDispose(
            catsLoadSubject.switchMapSingle { repository.getCatsList(page).subscribeOn(Schedulers.io()) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    val previousList = catsResultSubject.value ?: emptyList()
                    val resultList = previousList.toMutableList()
                        .apply {
                            addAll(it)
                        }
                    catsResultSubject.onNext(resultList)
                }, {
                    it.toString()
                })

        )
        unsubscribeOnDispose(
            favoritesIdsRepository.observeFavoritesChange()
                .subscribeOn(Schedulers.io())
                .subscribe { (favoriteCatId, isFavorite) ->
                    val newList = catsResultSubject.value?.map {
                        if (it.id == favoriteCatId) {
                            it.copy(isFavorites = isFavorite)
                        } else {
                            it
                        }
                    }
                    newList?.let {
                        catsResultSubject.onNext(newList)
                    }
                }
        )
    }

    override fun observeCats(): Observable<List<CatsModel>> {
        return catsResultSubject.doOnDispose { compositeDisposable.clear() }
    }

    override fun loadFirstPage() {
        page = 0
        catsLoadSubject.onNext(Unit)
    }

    override fun loadNextPage() {
        page++
        catsLoadSubject.onNext(Unit)
    }

    private fun unsubscribeOnDispose(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    override fun getCurrentList(): List<CatsModel>? = catsResultSubject.value
}