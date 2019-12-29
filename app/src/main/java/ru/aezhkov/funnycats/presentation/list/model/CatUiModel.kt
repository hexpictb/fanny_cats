package ru.aezhkov.funnycats.presentation.list.model

data class CatUiModel(
    val id: String,
    val url: String,
    val isFavorites: Boolean
) {
    var favoritesClickListener: ((ids: String) -> Unit)? = null
}
