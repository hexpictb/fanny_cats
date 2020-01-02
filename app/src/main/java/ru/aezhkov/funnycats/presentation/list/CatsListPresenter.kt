package ru.aezhkov.funnycats.presentation.list

import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import ru.aezhkov.funnycats.data.list.model.CatsModel
import ru.aezhkov.funnycats.domain.interactor.favorites.SwitchFavoritesUseCase
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListUseCase
import ru.aezhkov.funnycats.presentation.base.BasePresenter
import ru.aezhkov.funnycats.presentation.base.DownloadManagerWrapper
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import javax.inject.Inject
import ru.aezhkov.funnycats.R

@InjectViewState
class CatsListPresenter
@Inject constructor(
    private val getCatsListUseCase: GetCatsListUseCase,
    private val switchFavoritesUseCase: SwitchFavoritesUseCase,
    private val downloadManagerWrapper: DownloadManagerWrapper
) : BasePresenter<CatsListView>() {
    private var lastLongTapperModel: CatUiModel? = null

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
                    longClickListener = { model ->
                        lastLongTapperModel = model
                        viewState.checkWritePermission(model)
                    }
                }
        }
    }

    fun permissionGranted() {
        lastLongTapperModel?.let {
            downloadManagerWrapper.startDownload(it)
        }

    }

    private fun handleFavoriteClick(favoriteCatId: String) {
        unsubscribeOnDestroy(
            switchFavoritesUseCase.switchFavorites(favoriteCatId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ viewState.showMessage(R.string.switch_favorites_success) }, { viewState.showError(it) })
        )
    }

    fun loadMore() {
        getCatsListUseCase.loadNextPage()
    }


}