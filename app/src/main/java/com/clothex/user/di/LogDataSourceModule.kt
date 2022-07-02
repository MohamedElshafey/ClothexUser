package com.clothex.user.di

import com.clothex.data.log.repository.LogEventRepository
import com.clothex.data.log.repository.LogScreenRepository
import org.koin.dsl.module

val logDataSourceModule = module {
    single { LogScreenRepository() }
    single { LogEventRepository() }
}