package com.example.videojuegosapp.ui.videojuegos.vistas


import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.map
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.videojuegosapp.TabScreen
import com.example.videojuegosapp.data.mapeador.convertirADominio
import com.example.videojuegosapp.ui.videojuegos.componentes.VideoJuegoItem
import com.example.videojuegosapp.ui.videojuegos.favoritos
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


internal class VideoJuegosView (application: Application): Screen, KoinComponent {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel: VideoJuegoViewModel by inject()


        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TabScreen(viewModel, navigator)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VideoJuegosScreen(viewModel: VideoJuegoViewModel, navigator: Navigator) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit, block = {
        viewModel.obtenerVideoJuegos()
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {

        Column {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    items(state.value.videoJuegos.size) { i ->
                        VideoJuegoItem(videoJuego = state.value.videoJuegos[i], onClick = {
                            navigator.push(DetalleVideoJuegoView(videoJuego = it))
                        })
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarItem(
    modifier: Modifier = Modifier,
    onFilterClick: (String) -> Unit, showOptionsMenu: MutableState<Boolean>,
    filters: List<String>
) {

    TopAppBar(
        modifier = modifier.padding(
            top = 0.dp,
            start = 0.dp
        ),
        title = {
            Text(
                text = "FreeToGame",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface,
            actionIconContentColor = MaterialTheme.colorScheme.onSurface
        ),
        actions = {
            IconButton(onClick = { showOptionsMenu.value = !showOptionsMenu.value }) {
                Icon(Icons.Default.MoreVert, "Filters")
            }

            DropdownMenu(
                expanded = showOptionsMenu.value,
                onDismissRequest = { showOptionsMenu.value = false },
                modifier = modifier.background(MaterialTheme.colorScheme.background)
            ) {

                filters.forEachIndexed { index, item ->
                    DropdownMenuItem(onClick = {
                        showOptionsMenu.value = !showOptionsMenu.value
                        onFilterClick(filters[index])
                    }, text = {
                        Text(
                            text = item,
                            fontWeight = FontWeight.W300,
                            fontSize = 14.sp,
                        )
                    })
                }

            }

        }
    )
}