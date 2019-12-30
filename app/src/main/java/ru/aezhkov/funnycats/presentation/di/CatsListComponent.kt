package ru.aezhkov.funnycats.presentation.di

import dagger.Component
import ru.aezhkov.funnycats.data.di.DataLayerBinder
import ru.aezhkov.funnycats.data.di.NetworkModule
import ru.aezhkov.funnycats.data.di.ServiceModule
import ru.aezhkov.funnycats.domain.DomainLayerBinder
import ru.aezhkov.funnycats.presentation.favorites.FavoritesActivity
import ru.aezhkov.funnycats.presentation.list.MainActivity
import javax.inject.Singleton

@Component(
    modules = [
        NetworkModule::class,
        ServiceModule::class,
        DataLayerBinder::class,
        DomainLayerBinder::class
    ]
)
@Singleton
interface CatsListComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: FavoritesActivity)

}