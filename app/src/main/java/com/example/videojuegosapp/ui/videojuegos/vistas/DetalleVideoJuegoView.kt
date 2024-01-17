package com.example.videojuegosapp.ui.videojuegos.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.example.videojuegosapp.R
import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import com.example.videojuegosapp.dominio.modelo.VideoJuego
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class DetalleVideoJuegoView(
    private val videoJuego: VideoJuego
) : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val viewModel: VideoJuegoViewModel by inject()
        val state = viewModel.state.collectAsState()

        LaunchedEffect(true) {
            viewModel.obtenerDetalleVideoJuego(videoJuego.id)
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)) {

            Column(Modifier.verticalScroll(rememberScrollState())) {


                ItemCabeceraComposable(videoJuego = videoJuego, onClick = {
                    navigator.pop()
                })

                AsyncImage(
                    model  = videoJuego.miniatura,
                    contentDescription = "Anime",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    alignment = Alignment.Center,
                    alpha = DefaultAlpha,
                    filterQuality = DrawScope.DefaultFilterQuality,
                )

                PlayNowComponent(onClick = {
                    //to webview
                }, detalleVideoJuego = state.value.detalleVideoJuego)


                //AnimatedVisibility(!state.value.isLoading) {
                Column(
                    Modifier.padding(horizontal = 16.dp)
                ) {

                    Text(
                        text = stringResource(R.string.acerca_de, videoJuego.titulo),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = state.value.detalleVideoJuego?.descripcion ?: "",
                        fontSize = 15.sp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface
                    )

//                    Text(
//                        text = "Additional information", fontSize = 16.sp,
//                        fontWeight = FontWeight.SemiBold,
//                        color = MaterialTheme.colorScheme.onSurface
//                    )


                    Spacer(Modifier.height(24.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${videoJuego.titulo} Capturas", fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                    val capturaPantallas = state.value.detalleVideoJuego?.capturasPantalla ?: emptyList()
                    val height = (76 * capturaPantallas.size).dp
                    Spacer(Modifier.height(16.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.height(height),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(capturaPantallas.size) { i ->
                            AsyncImage(
                                model  = capturaPantallas[i].imagen,
                                contentDescription = "Anime",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(110.dp),
                                alignment = Alignment.Center,
                                alpha = DefaultAlpha,
                                filterQuality = DrawScope.DefaultFilterQuality,
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    Column(
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.descripci_n_requerimientos_de_sistema), fontSize = 17.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = videoJuego.plataforma, fontSize = 16.sp,
                            fontWeight = FontWeight.W200,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }


                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.so), fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.detalleVideoJuego?.requerimientosMinimosSistema?.so ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = stringResource(R.string.memoria), fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.detalleVideoJuego?.requerimientosMinimosSistema?.memoria ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = stringResource(R.string.procesador), fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.detalleVideoJuego?.requerimientosMinimosSistema?.procesador
                            ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Text(
                        text = stringResource(R.string.gr_ficos), fontSize = 13.sp,
                        fontWeight = FontWeight.W200,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = state.value.detalleVideoJuego?.requerimientosMinimosSistema?.graficos
                            ?: "",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W300,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                }
                //}

            }
        }


    }

}

@Composable
fun PlayNowComponent(
    modifier: Modifier = Modifier, onClick: (String) -> Unit, detalleVideoJuego: DetalleVideoJuego?,
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp, horizontal = 24.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = modifier
                .background(
                    MaterialTheme.colorScheme.surface, RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "FREE",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

        }

        Button(
            onClick = {
                detalleVideoJuego?.urlVideoJuego?.let { onClick(it) }
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer),
            elevation = ButtonDefaults.buttonElevation(1.dp),
            shape = RoundedCornerShape(8.dp), contentPadding = PaddingValues(16.dp)
        ) {
            Text(
                text = stringResource(R.string.jugar_ahora), fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
            Spacer(modifier = modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = null,
                modifier = modifier.size(ButtonDefaults.IconSize),
                tint = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}

@Composable
private fun ItemCabeceraComposable(
    modifier: Modifier = Modifier, videoJuego: VideoJuego, onClick: () -> Unit
) {
    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = "back navigate",
                tint = MaterialTheme.colorScheme.onSurface
            )
        }

        Text(
            text = videoJuego.titulo, fontSize = 18.sp, fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )


        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.ArrowBack, contentDescription = null,
                tint = MaterialTheme.colorScheme.surface
            )
        }
    }
}