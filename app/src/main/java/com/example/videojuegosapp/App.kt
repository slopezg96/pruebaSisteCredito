package com.example.videojuegosapp

import android.app.Application
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.example.videojuegosapp.ui.theme.VideoJuegosAppTheme
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel
import com.example.videojuegosapp.ui.videojuegos.vistas.VideoJuegosView

@Composable
fun App(
    darkTheme: Boolean, dynamicColor: Boolean, application: Application
) {
    VideoJuegosAppTheme(darkTheme = darkTheme, dynamicColor = dynamicColor) {
        Navigator(VideoJuegosView(application = application))
    }
}