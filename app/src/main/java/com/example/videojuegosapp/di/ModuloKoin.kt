package com.example.videojuegosapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun iniciarKoin(declaration: KoinAppDeclaration = {}) =
    startKoin {
        declaration
        modules(listOf(moduloComun(), platformModule()))
    }