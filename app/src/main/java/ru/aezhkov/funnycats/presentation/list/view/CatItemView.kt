package ru.aezhkov.funnycats.presentation.list.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.aezhkov.funnycats.R
import ru.aezhkov.funnycats.presentation.base.view.Widget
import ru.aezhkov.funnycats.presentation.list.model.CatUiModel

class CatItemView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), Widget<CatUiModel> {

    private val imageView by lazy { findViewById<ImageView>(R.id.cat_item_image) }

    override fun bind(model: CatUiModel) {
        Glide.with(context)
            .load(model.url)
            .centerCrop()
            .placeholder(R.drawable.ic_cat_placeholder)
            .into(imageView)
    }
}