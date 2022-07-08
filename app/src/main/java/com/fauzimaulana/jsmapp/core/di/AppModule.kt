package com.fauzimaulana.jsmapp.core.di

import com.fauzimaulana.jsmapp.core.data.source.JSMRepository
import com.fauzimaulana.jsmapp.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.jsmapp.core.data.source.remote.network.ApiService
import com.fauzimaulana.jsmapp.core.domain.repository.IJSMRepository
import com.fauzimaulana.jsmapp.core.domain.usecase.JSMInteractor
import com.fauzimaulana.jsmapp.core.domain.usecase.JSMUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val repositoryModule = module {
    single { RemoteDataSource(get()) }
    single<IJSMRepository> { JSMRepository(get()) }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val useCaseModule = module {
    factory<JSMUseCase> { JSMInteractor(get()) }
}