package ru.itis.tinkoff.project.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.itis.tinkoff.project.data.api.Api
import ru.itis.tinkoff.project.data.api.TokenApi
import ru.itis.tinkoff.project.data.database.local.PreferenceManager
import ru.itis.tinkoff.project.data.mapper.ResponseMapper
import ru.itis.tinkoff.project.data.repository.TokenRepository
import ru.itis.tinkoff.project.features.authorization.ui.AuthorizationViewModel
import ru.itis.tinkoff.project.features.cart.data.CartRepository
import ru.itis.tinkoff.project.features.cart.ui.CartFragmentViewModel
import ru.itis.tinkoff.project.features.catalog.data.CategoryRepository
import ru.itis.tinkoff.project.features.catalog.presentation.ui.viewModel.CategoryViewModel
import ru.itis.tinkoff.project.features.common.mapper.EntityMapper
import ru.itis.tinkoff.project.features.favorites.data.FavoritesRepository
import ru.itis.tinkoff.project.features.favorites.ui.FavoritesViewModel
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.features.main.ui.MainViewModel
import ru.itis.tinkoff.project.features.productPage.data.ProductPageRepository
import ru.itis.tinkoff.project.features.productPage.ui.ProductPageViewModel
import ru.itis.tinkoff.project.features.promotionPage.data.PromotionRepository
import ru.itis.tinkoff.project.features.promotionPage.ui.PromotionPageViewModel
import ru.itis.tinkoff.project.features.registration.data.RegistrationRepository
import ru.itis.tinkoff.project.features.registration.ui.RegistrationFragmentViewModel
import ru.itis.tinkoff.project.network.AuthInterceptor

const val API_URL = "https://market-app-technokratos.herokuapp.com/"
const val PREFERNCES_FILE_KEY = "ru.iti.tinkoff.project"
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
            get(),
            entityMapper = get(),
        )
    }
    viewModel<CartFragmentViewModel> {
        CartFragmentViewModel(
            cartRepository = get(),
            entityMapper = get()
        )
    }
    viewModel<ProductPageViewModel> {
        ProductPageViewModel(
            get(), get()
        )
    }
    viewModel<AuthorizationViewModel> {
        AuthorizationViewModel(
            get()
        )
    }
    viewModel<RegistrationFragmentViewModel> {
        RegistrationFragmentViewModel(
            get()
        )
    }
    viewModel<PromotionPageViewModel> {
        PromotionPageViewModel(
            get(),
            get()
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
    single<PromotionRepository> { PromotionRepository(get(), ResponseMapper()) }
    single<RegistrationRepository> { RegistrationRepository(get(), ResponseMapper()) }
    single<MenuRepository> { MenuRepository(api = get(), ResponseMapper()) }
    single<FavoritesRepository> { FavoritesRepository(api = get(), ResponseMapper()) }
    single<CartRepository> { CartRepository(api = get(), ResponseMapper()) }
    single<ProductPageRepository> { ProductPageRepository(get(), ResponseMapper()) }
    single<TokenRepository> {
        TokenRepository(
            tokenApi = get(), PreferenceManager(
                provideSharedPreferences(androidApplication())
            )
        )
    }
    single<CategoryRepository> { CategoryRepository(api = get()) }
}
val networkModule = module {
    single<TokenApi> { createTokenApi(provideRetrofit(get(named("TokenApiClient")))) }
    single<Api> { createApi(provideRetrofit(get(named("ApiClient")))) }
    single(named("ApiClient")) { provideOkHttpClient(get()) }
    single<Retrofit> { provideRetrofit(get()) }
    single<AuthInterceptor> { AuthInterceptor(get()) }
    single(named("TokenApiClient")) { provideOkHttpClientForTokenApi() }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(API_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun provideOkHttpClient(
    authInterceptor: AuthInterceptor
): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(authInterceptor).build()
}

fun provideOkHttpClientForTokenApi(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun createApi(retrofit: Retrofit): Api {
    return retrofit.create(Api::class.java)
}

fun createTokenApi(retrofit: Retrofit): TokenApi {
    return retrofit.create(TokenApi::class.java)
}

fun provideSharedPreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERNCES_FILE_KEY, Context.MODE_PRIVATE)
