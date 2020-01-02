package ru.aezhkov.funnycats.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.aezhkov.funnycats.data.favorites.FavoritesCatsDatabase
import ru.aezhkov.funnycats.data.favorites.dao.FavoritesCatsDao

private const val FAVORITES_DATABASE_NAME = "favorites"

@Module
class DbModule {
    @Provides
    fun provideDb(context: Context): FavoritesCatsDatabase {
        return Room.databaseBuilder(context, FavoritesCatsDatabase::class.java, FAVORITES_DATABASE_NAME).build()
    }

    @Provides
    fun provideFavoriteDao(database: FavoritesCatsDatabase): FavoritesCatsDao = database.dao
}