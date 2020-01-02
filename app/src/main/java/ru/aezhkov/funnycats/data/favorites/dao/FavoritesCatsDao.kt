package ru.aezhkov.funnycats.data.favorites.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import ru.aezhkov.funnycats.data.favorites.model.FavoriteCatEntity

@Dao
interface FavoritesCatsDao {

    @Query("SELECT * FROM favorites_cats")
    fun getAll(): Single<List<FavoriteCatEntity>>

    @Insert
    fun insert(entity: FavoriteCatEntity): Completable

    @Delete
    fun delete(entity: FavoriteCatEntity): Completable
}