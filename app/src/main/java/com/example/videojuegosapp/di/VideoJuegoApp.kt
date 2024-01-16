package com.example.videojuegosapp.di

import android.app.Application
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class VideoJuegoApp: Application() {

    override fun onCreate() {
        super.onCreate()

        iniciarKoin{
            androidLogger(level = if (BuildConfig.DEBUG) Level.INFO  else Level.NONE)
            androidContext(androidContext = this@VideoJuegoApp)
        }
    }
}