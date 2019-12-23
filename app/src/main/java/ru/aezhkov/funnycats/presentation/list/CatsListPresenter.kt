package ru.aezhkov.funnycats.presentation.list

import io.reactivex.android.schedulers.AndroidSchedulers
import moxy.InjectViewState
import ru.aezhkov.funnycats.data.list.model.CatsModel
import ru.aezhkov.funnycats.domain.interactor.list.GetCatsListUseCase
import ru.aezhkov.funnycats.presentation.base.BasePresenter
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import javax.inject.Inject

@InjectViewState
class CatsListPresenter
@Inject constructor(
    private val getCatsListUseCase: GetCatsListUseCase
) : BasePresenter<CatsListView>() {
    private var page = 0
    private val catsList = mutableListOf<CatUiModel>()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadCats()
    }

    private fun loadCats() {
        unsubscribeOnDestroy(
            getCatsListUseCase.getCats(page)
                .map { mapToUiModels(it) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    catsList.addAll(it)
                    viewState.updateList(catsList)
                }, { viewState.showError(it) })
        )
    }

    private fun mapToUiModels(list: List<CatsModel>): List<CatUiModel> {
        return list.map { CatUiModel(it.id, it.url) }
    }

    fun loadMore() {
        page++
        loadCats()
    }


}