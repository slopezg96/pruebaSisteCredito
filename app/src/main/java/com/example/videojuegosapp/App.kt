package com.example.videojuegosapp

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.videojuegosapp.ui.theme.VideoJuegosAppTheme
import com.example.videojuegosapp.ui.videojuegos.vistas.DashboardView

@Composable
fun App(
    darkTheme: Boolean, dynamicColor: Boolean
) {
    VideoJuegosAppTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
        Navigator(DashboardView())
    }
}