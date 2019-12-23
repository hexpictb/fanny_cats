package ru.aezhkov.funnycats.presentation.list.view

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OnLoadMoreListener(
    private val layoutManager: GridLayoutManager,
    private val action: () -> Unit
) : RecyclerView.OnScrollListener() {
    private var pastVisiblesItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) { // scroll down
            visibleItemCount = layoutManager.childCount;
            totalItemCount = layoutManager.itemCount;
            pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                action()
            }
        }
    }
}
