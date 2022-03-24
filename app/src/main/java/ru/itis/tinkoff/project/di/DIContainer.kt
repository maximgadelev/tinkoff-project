package ru.itis.tinkoff.project.di

import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.itis.tinkoff.project.BuildConfig
import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.domain.usecases.GetUserUseCase
import ru.itis.tinkoff.project.features.profile.data.TestUserRepository
import ru.itis.tinkoff.project.features.profile.data.UserRepositoryImpl

object DIContainer  {
    private const val BASE_URL = "https://url"
    private val okhttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(
                                HttpLoggingInterceptor.Level.BODY
                            )
                    )
                }
            }
            .build()
    }
    val api: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okhttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    val userRepository : UserRepository = UserRepositoryImpl(
        api = api
    )

    val getUserUseCase : GetUserUseCase = GetUserUseCase(
     repository = TestUserRepository, //don't forget to change  (userRepository)
     scope = Dispatchers.Default
    )
}
