package ru.aezhkov.funnycats.domain.interactor.favorites

import ru.aezhkov.funnycats.data.favorites.FavoritesIdsRepository
import javax.inject.Inject

class FavoritesInteractor
@Inject constructor(
    private val repository: FavoritesIdsRepository
) : SwitchFavoritesUseCase {

    override fun switchFavorites(id: String) {

    }
}