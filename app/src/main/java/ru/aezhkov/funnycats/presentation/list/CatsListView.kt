package ru.aezhkov.funnycats.presentation.list

import androidx.annotation.StringRes
import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel

@StateStrategyType(AddToEndSingleStrategy::class)
interface CatsListView : MvpView {

    fun updateList(list: List<CatUiModel>)

    fun showError(throwable: Throwable)

    fun showMessage(@StringRes messageResId: Int)

    fun checkWritePermission(model: CatUiModel)
}