package ru.aezhkov.funnycats.domain.interactor.favorites

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import ru.aezhkov.funnycats.data.favorites.FavoritesIdsRepository
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListUseCase
import javax.inject.Inject

class FavoritesInteractor
@Inject constructor(
    private val repository: FavoritesIdsRepository,
    private val listUseCase: GetCatsListUseCase
) : SwitchFavoritesUseCase {

    override fun switchFavorites(id: String): Completable {
        val catModel = listUseCase.getCurrentList()?.findLast { it.id == id }
        val completable = if (catModel != null) {
            if (catModel.isFavorites) {
                repository.removeFromFavorites(catModel)
            } else {
                repository.addToFavorites(catModel)
            }
        } else {
            // log or something else
            Completable.complete()
        }
        return completable.subscribeOn(Schedulers.io())
    }

}