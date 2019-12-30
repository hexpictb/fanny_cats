package ru.aezhkov.funnycats.domain.interactor.favorites

import ru.aezhkov.funnycats.data.favorites.FavoritesIdsRepository
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListUseCase
import javax.inject.Inject

class FavoritesInteractor
@Inject constructor(
    private val repository: FavoritesIdsRepository,
    private val listUseCase: GetCatsListUseCase
) : SwitchFavoritesUseCase {

    override fun switchFavorites(id: String) {
        listUseCase.getCurrentList()?.findLast { it.id == id }?.let {
            if (it.isFavorites) {
                repository.removeFromFavorites(it)
            } else {
                repository.addToFavorites(it)
            }
        }
    }

}