package com.example.videojuegosapp.di

import org.koin.core.module.Module
import org.koin.dsl.module
import io.ktor.client.engine.android.Android

fun platformModule(): Module = module {
    single { Android.create() }
}