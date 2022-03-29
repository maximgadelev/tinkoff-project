package ru.itis.tinkoff.project.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.data.response.TestUserApi
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.features.profile.data.UserIdRepository
import ru.itis.tinkoff.project.features.profile.data.UserRepositoryImpl
import ru.itis.tinkoff.project.features.profile.viewModel.UserViewModel

val appModule = module {
    single<UserIdRepository> { UserIdRepository() }
    viewModel<UserViewModel> {
        UserViewModel(
            userRepository = get(),
            userIdRepository = get()
        )
    }
}

val dataModule = module {
    single<Api> { TestUserApi() }
    single<UserRepository> { UserRepositoryImpl(api = get()) }
    single<UserIdRepository> { UserIdRepository() }
}

