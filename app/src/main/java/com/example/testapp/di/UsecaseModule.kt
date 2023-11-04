package com.example.testapp.di

import com.example.testapp.data.usecase.MainUsecaseImpl
import com.example.testapp.domain.MainUsecase
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MAIN_USECASE_NAME = "MainUsecase"
val usecaseModule = module {

    factory<MainUsecase>(qualifier = named(MAIN_USECASE_NAME)) {
        MainUsecaseImpl(get(named(REPOSITORY_IMPL)))
    }

}