package com.clothex.user.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.clothex.data.local.room.dao.SavedLocationDao
import com.clothex.data.local.shared_pref.LocalDataSourceImpl
import com.clothex.data.remote.api.*
import com.clothex.user.BuildConfig
import com.clothex.user.network.HeadersInterceptor
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

    single {
        provideOkHttpClient(chuckerInterceptor = get(), headersInterceptor = get())
    }

    single { provideHomeApiService(retrofit = get()) }

    single { provideProductApiService(retrofit = get()) }

    single { provideShopApiService(retrofit = get()) }

    single { provideMyItemApiService(retrofit = get()) }

    single { provideMyOrderApiService(retrofit = get()) }

    single { provideSignApiService(retrofit = get()) }

    single { provideTokenInterceptor(localDataSourceImpl = get(), dao = get()) }
}


internal fun provideOkHttpClient(
    chuckerInterceptor: ChuckerInterceptor,
    headersInterceptor: HeadersInterceptor
): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val builder = OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(headersInterceptor)
    if (BuildConfig.DEBUG) {
        builder.addInterceptor(chuckerInterceptor)
    }
    return builder.build()
}

internal fun provideTokenInterceptor(
    localDataSourceImpl: LocalDataSourceImpl,
    dao: SavedLocationDao
): HeadersInterceptor {
    return HeadersInterceptor(localDataSourceImpl, dao)
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
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        .create()
}

internal fun provideHomeApiService(retrofit: Retrofit): HomeApiService =
    retrofit.create(HomeApiService::class.java)

internal fun provideProductApiService(retrofit: Retrofit): ProductApiService =
    retrofit.create(ProductApiService::class.java)

internal fun provideShopApiService(retrofit: Retrofit): ShopApiService =
    retrofit.create(ShopApiService::class.java)

internal fun provideMyItemApiService(retrofit: Retrofit): MyItemApiService =
    retrofit.create(MyItemApiService::class.java)

internal fun provideMyOrderApiService(retrofit: Retrofit): OrdersApiService =
    retrofit.create(OrdersApiService::class.java)

internal fun provideSignApiService(retrofit: Retrofit): SigningApiService =
    retrofit.create(SigningApiService::class.java)