package com.example.videojuegosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import cafe.adriel.voyager.navigator.Navigator
import com.example.videojuegosapp.ui.theme.VideoJuegosAppTheme
import com.example.videojuegosapp.ui.videojuegos.viewmodel.VideoJuegoViewModel
import com.example.videojuegosapp.ui.videojuegos.vistas.FavoritosScreen
import com.example.videojuegosapp.ui.videojuegos.vistas.VideoJuegosScreen
import org.koin.java.KoinJavaComponent

class MainActivity : ComponentActivity() {

    //private val viewModel: VideoJuegoViewModel by viewModels()
    val viewModel: VideoJuegoViewModel by KoinJavaComponent.inject(VideoJuegoViewModel::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.favoritos.observe(this, Observer { favoritos ->
            print(favoritos.size)
        })
        setContent {

            App(darkTheme = isSystemInDarkTheme(), dynamicColor = true, application)

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
            1 -> FavoritosScreen( viewModel = viewModel, navigator = navigator)
        }
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoJuegosAppTheme {

    }
}