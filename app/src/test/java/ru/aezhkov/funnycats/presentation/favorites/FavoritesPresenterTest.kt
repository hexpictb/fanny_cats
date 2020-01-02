package ru.aezhkov.funnycats.presentation.favorites

import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import ru.aezhkov.funnycats.data.list.model.CatsModel
import ru.aezhkov.funnycats.domain.interactor.favorites.FavoritesListUseCase

class FavoritesPresenterTest {
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    private val favoritesUseCase = mockk<FavoritesListUseCase>(relaxed = true)

    private val viewState = mockk<`FavoritesView$$State`>(relaxed = true)

    private val presenter = FavoritesPresenter(favoritesUseCase)

    @Test
    fun `should show list of favorites cats of attach`() {
        every { favoritesUseCase.getFavoritesCatsList() } returns Single.just(listOf(CatsModel("123", "url", true)))

        presenter.setViewState(viewState)
        presenter.attachView(mockk(relaxed = true))

        verify { viewState.updateCards(any()) }
    }
}