package com.example.videojuegosapp.ui.videojuegos.vistas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.example.videojuegosapp.TabScreen
import com.example.videojuegosapp.ui.theme.VideoJuegosAppTheme
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel
import org.koin.core.component.KoinComponent
import org.koin.java.KoinJavaComponent.inject

internal class DashboardView: Screen, KoinComponent {

    @Composable
    override fun Content() {
        val viewModel: VideoJuegoViewModel by inject(VideoJuegoViewModel::class.java)

        VideoJuegosAppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                TabScreen(viewModel)
            }
        }
    }
}