package ru.itis.tinkoff.project.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.itis.tinkoff.project.data.Api
import ru.itis.tinkoff.project.features.main.presentation.mapper.EntityMapper
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.data.StubApi
import ru.itis.tinkoff.project.domain.repositories.UserRepository
import ru.itis.tinkoff.project.features.main.presentation.ui.MainViewModel
import ru.itis.tinkoff.project.features.profile.data.UserIdRepository
import ru.itis.tinkoff.project.features.profile.data.UserRepositoryImpl
import ru.itis.tinkoff.project.features.profile.presentation.UserViewModel

val appModule = module {
    single<EntityMapper> { EntityMapper() }
    viewModel<MainViewModel> {
        MainViewModel(
            menuRepository = get(),
            entityMapper = get()
        )
    }
    viewModel<UserViewModel>{
        UserViewModel(
            userRepository = get(),
            userIdRepository = get(),
        )
    }
}
val dataModule = module {
    single<Api> { StubApi() }
    single<MenuRepository> { MenuRepository(api = get()) }
    single<UserRepository>{ UserRepositoryImpl(api = get()) }
    single<UserIdRepository>{UserIdRepository()}
}
