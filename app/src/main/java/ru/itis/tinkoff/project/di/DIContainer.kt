package ru.itis.tinkoff.project.di

import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.tinkoff.project.BuildConfig
import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.data.StubApi
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.ui.FavoritesViewModel
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.features.main.ui.MainViewModel
import ru.itis.tinkoff.project.network.BaseInterceptor

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
val networkModule = module {
    factory { BaseInterceptor() }
    factory<OkHttpClient> { provideOkHttpClient(get()) }
    single<Retrofit> { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.VERSION_NAME).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(baseInterceptor: BaseInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(baseInterceptor).build()
}
