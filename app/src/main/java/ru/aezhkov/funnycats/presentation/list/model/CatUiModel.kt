package ru.aezhkov.funnycats.presentation.list.model

data class CatUiModel(
    val id: String,
    val url: String,
    val isFavorites: Boolean
) {
    var favoritesClickListener: ((id: String) -> Unit)? = null
    var longClickListener: ((model: CatUiModel) -> Unit)? = null
}
