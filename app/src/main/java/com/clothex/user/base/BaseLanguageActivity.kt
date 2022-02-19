package com.clothex.user.base

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BaseLanguageActivity : AppCompatActivity() {

    private val viewModel = LanguageViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val lang = viewModel.getLanguage()
        val config = resources.configuration
        val locale = Locale(lang)
        Locale.setDefault(locale)
        config.setLocale(locale)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
            createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)
        super.onCreate(savedInstanceState)
    }

}