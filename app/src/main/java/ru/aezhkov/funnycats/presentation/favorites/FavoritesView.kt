package ru.aezhkov.funnycats.presentation.favorites

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel

interface FavoritesView : MvpView {
    @StateStrategyType(AddToEndSingleStrategy::class)
    fun updateCards(list: List<CatUiModel>)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showEmptyMessage()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showError(throwable: Throwable)
}