package com.example.videojuegosapp

// for a `var` variable also add

// or just
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.videojuegosapp.ui.theme.VideoJuegosAppTheme
import com.example.videojuegosapp.ui.videojuegos.componentes.VideoJuegoItem
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App(darkTheme = isSystemInDarkTheme(), dynamicColor = true)
        }
    }
}

@Composable
fun TabScreen(viewModel: VideoJuegoViewModel) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(stringResource(R.string.todos), stringResource(R.string.favoritos))

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> VideoJuegosScreen( viewModel = viewModel )
        }
    }
}

/*@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun VideoJuegosScreen(viewModel: VideoJuegoViewModel){

    when (val result = viewModel._videoJuegos.value){
        is VideoJuegoState.Cargando -> {
            CircularProgressIndicator()
        }

        is VideoJuegoState.Exitoso -> {
            LazyColumn {
                items(result.data) { response ->
                    VideoGameCard(videoJuego = response)
                }
            }
        }

        is VideoJuegoState.Error -> {
            Text(text = "${result.message}")
        }
    }
}*/


@Composable
fun VideoJuegosScreen(viewModel: VideoJuegoViewModel ) {

    val state = viewModel.state.collectAsState()
    LaunchedEffect(Unit, block = {
        viewModel.obtenerVideoJuegos()
    })

    LazyColumn {
        itemsIndexed(items = state.value.videoJuegos) { index, item ->
            VideoJuegoItem(videoJuego = item, onClick = {

            })

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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoJuegosAppTheme {

    }
}