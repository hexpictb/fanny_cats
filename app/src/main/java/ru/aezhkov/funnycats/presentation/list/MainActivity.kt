package ru.aezhkov.funnycats.presentation.list

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import ru.aezhkov.funnycats.R
import ru.aezhkov.funnycats.presentation.di.ApplicationModule
import ru.aezhkov.funnycats.presentation.di.DaggerCatsListComponent
import ru.aezhkov.funnycats.presentation.favorites.FavoritesActivity
import ru.aezhkov.funnycats.presentation.list.adapter.CatsListAdapter
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import ru.aezhkov.funnycats.presentation.list.view.OnLoadMoreListener
import javax.inject.Inject

private const val COLUMNS_COUNT = 3
private const val WRITE_PERMISSION_REQUEST_CODE = 123

class MainActivity : MvpAppCompatActivity(), CatsListView {

    @Inject
    @InjectPresenter
    lateinit var presenter: CatsListPresenter

    @ProvidePresenter
    fun providePresenter(): CatsListPresenter = presenter

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.cats_list_recycler) }
    private val adapter = CatsListAdapter()
    private val toolbar by lazy { findViewById<Toolbar>(R.id.cats_list_toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCatsListComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build().inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter
        val gridLayoutManager = GridLayoutManager(this, COLUMNS_COUNT)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.addOnScrollListener(OnLoadMoreListener(gridLayoutManager) {
            presenter.loadMore()
        })
        toolbar.inflateMenu(R.menu.cats_menu)
        toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.favorites_menu_item) {
                startActivity(Intent(this, FavoritesActivity::class.java))
            }
            return@setOnMenuItemClickListener true
        }
    }

    override fun updateList(list: List<CatUiModel>) {
        adapter.submitList(list)
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, throwable.message, Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(messageResId: Int) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_SHORT).show()
    }

    override fun checkWritePermission(model: CatUiModel) {
        val permissionState = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (permissionState != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                WRITE_PERMISSION_REQUEST_CODE
            )
        } else {
            presenter.permissionGranted()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WRITE_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                presenter.permissionGranted()
            }
        }
    }
}
