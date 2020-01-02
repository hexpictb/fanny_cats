package ru.aezhkov.funnycats.presentation.favorites

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aezhkov.funnycats.R
import ru.aezhkov.funnycats.presentation.di.ApplicationModule
import ru.aezhkov.funnycats.presentation.di.DaggerCatsListComponent
import ru.aezhkov.funnycats.presentation.list.adapter.CatsListAdapter
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import javax.inject.Inject

private const val COLUMNS_COUNT = 3

class FavoritesActivity : MvpAppCompatActivity(), FavoritesView {

    @Inject
    @InjectPresenter
    lateinit var presenter: FavoritesPresenter

    @ProvidePresenter
    fun providePresenter(): FavoritesPresenter = presenter

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.favorites_recycler) }
    private val adapter = CatsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCatsListComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favorite_screen_layout)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, COLUMNS_COUNT)
    }

    override fun updateCards(list: List<CatUiModel>) {
        adapter.submitList(list)
    }

    override fun showEmptyMessage() {
        Toast.makeText(this, getString(R.string.favorites_empty_message), Toast.LENGTH_SHORT).show()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}