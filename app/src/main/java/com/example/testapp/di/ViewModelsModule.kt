package com.example.testapp.di

import com.example.testapp.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MAIN_VIEW_MODEL = "MainViewModel"

val viewModelsModule = module {
    viewModel(qualifier = named(MAIN_VIEW_MODEL)) {
        MainViewModel(
            usecase = get(named(MAIN_USECASE_NAME))
        )
    }
}