package com.example.videojuegosapp.ui.videojuegos.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LaptopWindows
import androidx.compose.material.icons.filled.WebStories
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.videojuegosapp.R
import com.example.videojuegosapp.dominio.modelo.VideoJuego

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoJuegoItem(
    modifier: Modifier = Modifier,
    videoJuego: VideoJuego, onClick: (VideoJuego) -> Unit
) {

    Card(
        onClick = {
            onClick(videoJuego)
        },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = CardDefaults.cardElevation(0.5.dp),
        shape = RoundedCornerShape(14.dp),
    ) {
        Column {
            AsyncImage(
                model = videoJuego.miniatura,
                contentDescription = "VideoJuego",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                modifier = modifier
                    .fillMaxWidth()
                    .height(220.dp),
                alignment = Alignment.Center,
                alpha = DefaultAlpha,
                filterQuality = DrawScope.DefaultFilterQuality,
            )

            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = videoJuego.titulo, fontSize = 17.sp, fontWeight = FontWeight.SemiBold)

                Row(
                    modifier = modifier
                        .background(
                            MaterialTheme.colorScheme.secondary, RoundedCornerShape(4.dp)
                        )
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "FREE",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.SemiBold
                    )

                }
            }

            Text(
                text = videoJuego.descripcionCorta, fontSize = 15.sp,
                maxLines = 1, modifier = modifier.padding(horizontal = 8.dp, vertical = 8.dp),
                fontWeight = FontWeight.W300
            )


            Row(
                modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 16.dp, start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = modifier
                        .background(
                            MaterialTheme.colorScheme.secondary, RoundedCornerShape(4.dp)
                        )
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = videoJuego.genero,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                val icon = if (videoJuego.plataforma.lowercase().contains(stringResource(R.string.pc))) {
                    Icons.Default.LaptopWindows
                } else if (videoJuego.plataforma.lowercase().contains(stringResource(R.string.browser))) {
                    Icons.Default.WebStories
                } else {
                    null
                }

                icon?.let {
                    Icon(
                        imageVector = it, contentDescription = "Filters",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = modifier
                            .padding(start = 8.dp)
                            .size(18.dp)
                    )
                }


            }
        }
    }

}