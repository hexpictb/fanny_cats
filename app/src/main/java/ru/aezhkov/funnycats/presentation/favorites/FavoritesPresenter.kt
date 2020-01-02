package ru.aezhkov.funnycats.presentation.favorites

import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import ru.aezhkov.funnycats.data.list.model.CatsModel
import ru.aezhkov.funnycats.domain.interactor.favorites.FavoritesListUseCase
import ru.aezhkov.funnycats.domain.interactor.favorites.SwitchFavoritesUseCase
import ru.aezhkov.funnycats.presentation.base.BasePresenter
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import javax.inject.Inject

@InjectViewState
class FavoritesPresenter
@Inject constructor(
    private val favoritesUseCase: FavoritesListUseCase
) : BasePresenter<FavoritesView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        unsubscribeOnDestroy(
            favoritesUseCase.getFavoritesCatsList()
                .map { mapToUiModels(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it.isNotEmpty()) {
                        viewState.updateCards(it)
                    } else {
                        viewState.showEmptyMessage()
                    }
                }, { viewState.showError(it) })
        )
    }

    private fun mapToUiModels(list: List<CatsModel>): List<CatUiModel> {
        return list.map {
            CatUiModel(it.id, it.url, it.isFavorites)
        }
    }

}