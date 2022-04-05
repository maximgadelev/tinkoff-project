package ru.itis.tinkoff.project.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.data.StubApi
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.ui.FavoritesViewModel
import ru.itis.tinkoff.project.features.main.ui.MainViewModel

val appModule = module {
    single<EntityMapper> { EntityMapper() }
    viewModel<MainViewModel> {
        MainViewModel(
            menuRepository = get(),
            entityMapper = get()
        )
    }
    viewModel<FavoritesViewModel> {
        FavoritesViewModel(
            favoritesRepository = get(),
            entityMapper = get()
        )
    }
}
val dataModule = module {
    single<Api> { StubApi() }
    single<MenuRepository> { MenuRepository(api = get()) }
    single<FavoritesRepository> { FavoritesRepository(api = get()) }
}


