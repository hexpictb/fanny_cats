package ru.aezhkov.funnycats.presentation.list

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aezhkov.funnycats.R
import ru.aezhkov.funnycats.presentation.di.CatsListComponent
import ru.aezhkov.funnycats.presentation.di.DaggerCatsListComponent
import ru.aezhkov.funnycats.presentation.list.adapter.CatsListAdapter
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import ru.aezhkov.funnycats.presentation.list.view.OnLoadMoreListener
import javax.inject.Inject

private const val COLUMNS_COUNT = 3

class MainActivity : MvpAppCompatActivity(), CatsListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: CatsListPresenter

    @ProvidePresenter
    fun providePresenter(): CatsListPresenter = presenter

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.cats_list_recycler) }
    private val adapter = CatsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCatsListComponent.builder().build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter
        val gridLayoutManager = GridLayoutManager(this, COLUMNS_COUNT)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addOnScrollListener(OnLoadMoreListener(gridLayoutManager) {
            presenter.loadMore()
        })
    }

    override fun updateList(list: List<CatUiModel>) {
        adapter.submitList(list)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }
}
