package ru.aezhkov.funnycats.data.favorites

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.aezhkov.funnycats.data.favorites.dao.FavoritesCatsDao
import ru.aezhkov.funnycats.data.favorites.model.FavoriteCatEntity

@Database(entities = [FavoriteCatEntity::class], version = 1)
abstract class FavoritesCatsDatabase : RoomDatabase() {
    abstract val dao: FavoritesCatsDao
}