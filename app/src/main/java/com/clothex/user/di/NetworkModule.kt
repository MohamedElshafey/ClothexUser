package com.clothex.user.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.clothex.data.remote.api.HomeApiService
import com.clothex.data.remote.api.ProductApiService
import com.clothex.user.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Mohamed Elshafey on 10/9/2020.
 */

val networkModule = module {

    single { provideGson() }

    single { provideRetrofit(okHttpClient = get(), gson = get(), url = BuildConfig.BASE_URL) }

    single { provideChuckerInterceptor(context = androidContext()) }

    single { provideOkHttpClient(chuckerInterceptor = get()) }

    single { provideHomeApiService(retrofit = get()) }

    single { provideProductApiService(retrofit = get()) }
}


internal fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val builder = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(chuckerInterceptor)
    }
    return builder.build()
}

internal fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
    return ChuckerInterceptor.Builder(context).build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, url: String, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

internal fun provideGson(): Gson {
    return GsonBuilder()
        .setLenient()
        .create()
}

internal fun provideHomeApiService(retrofit: Retrofit): HomeApiService =
    retrofit.create(HomeApiService::class.java)

internal fun provideProductApiService(retrofit: Retrofit): ProductApiService =
    retrofit.create(ProductApiService::class.java)