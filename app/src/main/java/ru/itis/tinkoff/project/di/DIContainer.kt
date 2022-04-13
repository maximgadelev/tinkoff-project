package ru.itis.tinkoff.project.di

import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.features.cart.data.CartRepository
import ru.itis.tinkoff.project.features.cart.ui.CartFragmentViewModel
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.ui.FavoritesViewModel
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.features.main.ui.MainViewModel
import ru.itis.tinkoff.project.network.AuthInterceptor

const val API_URL = "https://market-app-technokratos.herokuapp.com/"
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
}
val dataModule = module {
    single<MenuRepository> { MenuRepository(api = get(), ResponseMapper()) }
    single<FavoritesRepository> { FavoritesRepository(api = get(), ResponseMapper()) }
    single<CartRepository> { CartRepository(api = get(), ResponseMapper()) }
}
val networkModule = module {
    single<Api> { createApi(get()) }
    factory<AuthInterceptor> { AuthInterceptor() }
    single<OkHttpClient> { provideOkHttpClient(get()) }
    single<Retrofit> { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}
fun createApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}
