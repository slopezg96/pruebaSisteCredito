package com.example.videojuegosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.videojuegosapp.ui.videojuegos.detalleVideoJuego.DetalleVideoJuegoView
import com.example.videojuegosapp.ui.theme.VideoJuegosAppTheme
import com.example.videojuegosapp.ui.videojuegos.componentes.VideoJuegoItem
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel
import com.example.videojuegosapp.ui.videojuegos.vistas.VideoJuegosScreen
import org.koin.java.KoinJavaComponent

class MainActivity : ComponentActivity() {

    //private val viewModel: VideoJuegoViewModel by viewModels()
    val viewModel: VideoJuegoViewModel by KoinJavaComponent.inject(VideoJuegoViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            App(darkTheme = isSystemInDarkTheme(), dynamicColor = true)

        }


    }
}

@Composable
fun TabScreen(viewModel: VideoJuegoViewModel, navigator: Navigator) {
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
            0 -> VideoJuegosScreen( viewModel = viewModel, navigator = navigator)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoJuegosAppTheme {

    }
}