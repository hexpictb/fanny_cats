package ru.aezhkov.funnycats.data.favorites.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_cats")
data class FavoriteCatEntity(
    @PrimaryKey
    val id: String,
    val url: String
)