package ru.aezhkov.funnycats.presentation.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel
import ru.aezhkov.funnycats.R
import ru.aezhkov.funnycats.presentation.list.view.CatItemView

class CatsListAdapter : ListAdapter<CatUiModel, CatsViewHolder>(CatsDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CatsViewHolder(inflater.inflate(R.layout.cat_item_view, parent, false))
    }

    override fun onBindViewHolder(holder: CatsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CatsDiffCallback : DiffUtil.ItemCallback<CatUiModel>() {
    override fun areItemsTheSame(oldItem: CatUiModel, newItem: CatUiModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CatUiModel, newItem: CatUiModel): Boolean {
        return oldItem == newItem
    }
}

class CatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(model: CatUiModel) {
        itemView as CatItemView
        itemView.bind(model)
    }
}