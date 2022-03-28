package ru.itis.tinkoff.project.di

import ru.itis.tinkoff.project.features.main.presentation.mapper.EntityMapper
import ru.itis.tinkoff.project.features.main.data.MenuRepository
import ru.itis.tinkoff.project.data.StubApi

object DIContainer {

    val entityMapper = EntityMapper()
    val testApiImpl = StubApi()
    val menuRepository = MenuRepository(testApiImpl)
}
