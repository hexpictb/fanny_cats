package ru.aezhkov.funnycats.presentation.list

import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import ru.aezhkov.funnycats.data.list.model.CatsModel
import ru.aezhkov.funnycats.domain.interactor.favorites.SwitchFavoritesUseCase
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListUseCase
import ru.aezhkov.funnycats.presentation.base.BasePresenter
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import javax.inject.Inject

@InjectViewState
class CatsListPresenter
@Inject constructor(
    private val getCatsListUseCase: GetCatsListUseCase,
    private val switchFavoritesUseCase: SwitchFavoritesUseCase
) : BasePresenter<CatsListView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        unsubscribeOnDestroy(
            getCatsListUseCase.observeCats()
                .map { mapToUiModels(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.updateList(it) }, { viewState.showError(it) })
        )
        getCatsListUseCase.loadFirstPage()
    }

    private fun mapToUiModels(list: List<CatsModel>): List<CatUiModel> {
        return list.map {
            CatUiModel(it.id, it.url, it.isFavorites)
                .apply {
                    favoritesClickListener = { favoriteCatId -> handleFavoriteClick(favoriteCatId) }
                }
        }
    }

    private fun handleFavoriteClick(favoriteCatId: String) {
        switchFavoritesUseCase.switchFavorites(favoriteCatId)
    }

    fun loadMore() {
        getCatsListUseCase.loadNextPage()
    }


}