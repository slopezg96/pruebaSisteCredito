package com.example.videojuegosapp.ui.videojuegos.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import cafe.adriel.voyager.navigator.Navigator
import com.example.videojuegosapp.data.mapeador.convertirADominio
import com.example.videojuegosapp.ui.videojuegos.componentes.VideoJuegoItem
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel

@Composable
fun FavoritosScreen(viewModel: VideoJuegoViewModel, navigator: Navigator) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit, block = {

    })

    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface),
    ) {

        Column {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    viewModel.favoritos.value?.size?.let {
                        items(it) { i ->
                            viewModel.favoritos.value?.map { it.convertirADominio() }?.let { it1 ->
                                VideoJuegoItem(videoJuego = it1.get(i), onClick = {
                                    navigator.push(DetalleVideoJuegoView(videoJuego = it))
                                })
                            }
                        }
                    }
                }
            }

            if (state.value.estaCargando) {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.tertiaryContainer)
                }
            }
        }
    }

}