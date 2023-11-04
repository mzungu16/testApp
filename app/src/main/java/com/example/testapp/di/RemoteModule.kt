package com.example.testapp.di

import android.app.Application
import androidx.room.Room
import com.example.testapp.data.database.FoodDao
import com.example.testapp.data.database.FoodDatabase
import com.example.testapp.data.datasource.remote.RemoteDataSourceImpl
import com.example.testapp.data.mapper.MapperImpl
import com.example.testapp.data.repository.RepositoryImpl
import com.example.testapp.data.retrofit.MealApi
import com.example.testapp.domain.Mapper
import com.example.testapp.domain.RemoteDataSource
import com.example.testapp.domain.Repository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val OKHTTP_CLIENT = "OkHttpClient"
const val RETROFIT_INIT = "RetrofitInit"
const val RETROFIT_INT = "RetrofitInt"
const val REMOTE_DATA_SOURCE_IMPL = "RemoteDataSource"
const val REPOSITORY_IMPL = "RepositoryImpl"
const val BOOK_DAO = "BookDao"
const val APP_MAPPER = "LocalMapper"
const val BOOK_DATABASE = "BookDatabase"

val remoteModule = module {
    fun provideDatabase(application: Application): FoodDatabase {
        return Room.databaseBuilder(application, FoodDatabase::class.java, "books")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideBookDao(database: FoodDatabase): FoodDao {
        return database.foodDao()
    }

    single(qualifier = named(BOOK_DATABASE)) { provideDatabase(androidApplication()) }

    single(qualifier = named(BOOK_DAO)) { provideBookDao(get(named(BOOK_DATABASE))) }

    factory<Mapper>(qualifier = named(APP_MAPPER)) { MapperImpl() }

    single(qualifier = named(OKHTTP_CLIENT)) {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    single(qualifier = named(RETROFIT_INIT)) {
        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/")
            .client(get(named(OKHTTP_CLIENT)))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<MealApi>(qualifier = named(RETROFIT_INT)) {
        get<Retrofit>(named(RETROFIT_INIT)).create(
            MealApi::class.java
        )
    }

    single<RemoteDataSource>(qualifier = named(REMOTE_DATA_SOURCE_IMPL)) {
        RemoteDataSourceImpl(
            get(named(RETROFIT_INT)),
            get(named(APP_MAPPER))
        )
    }

    single<Repository>(qualifier = named(REPOSITORY_IMPL)) {
        RepositoryImpl(get(named(REMOTE_DATA_SOURCE_IMPL)))
    }
}