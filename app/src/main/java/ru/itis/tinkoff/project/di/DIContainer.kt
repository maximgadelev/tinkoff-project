package ru.itis.tinkoff.project.di

import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.data.StubApi
import ru.itis.tinkoff.project.features.cart.data.CartRepository
import ru.itis.tinkoff.project.features.cart.ui.CartFragmentViewModel
import ru.itis.tinkoff.project.features.catalog.data.CategoryRepository
import ru.itis.tinkoff.project.features.catalog.presentation.ui.viewModel.CategoryViewModel
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.ui.FavoritesViewModel
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.features.main.ui.MainViewModel

const val API_URL = "market-app-technokratos.herokuapp.com/"
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
    viewModel<CartFragmentViewModel> {
        CartFragmentViewModel(
            cartRepository = get(),
            entityMapper = get()
        )
    }

    viewModel<CategoryViewModel> {
        CategoryViewModel(
            categoryRepository = get(),
            entityMapper = get()
        )
    }
}
val dataModule = module {
    single<Api> { StubApi() }
    single<MenuRepository> { MenuRepository(api = get()) }
    single<FavoritesRepository> { FavoritesRepository(api = get()) }
    single<CartRepository> { CartRepository(api = get()) }
    single<CategoryRepository> { CategoryRepository(api = get()) }
}
val networkModule = module {
    single<OkHttpClient> { provideOkHttpClient() }
    single<Retrofit> { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}
