package com.example.videojuegosapp.ui.videojuegos.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.videojuegosapp.data.ResultadoRed
import com.example.videojuegosapp.data.basedatos.entidades.VideoJuegoEntidad
import com.example.videojuegosapp.data.mapeador.convertirADominio
import com.example.videojuegosapp.data.repositorio.VideoJuegoRepositorioLocal
import com.example.videojuegosapp.dominio.repositorio.VideoJuegoRepositorio
import com.example.videojuegosapp.ui.videojuegos.state.VideoJuegoState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext


class VideoJuegoViewModel(
    private val videojuegoRepositorio: VideoJuegoRepositorio,
    private val videoJuegoRepositorioLocal: VideoJuegoRepositorioLocal
) : StateScreenModel<VideoJuegoState>(VideoJuegoState()) {

    var favoritos: LiveData<List<VideoJuegoEntidad>> = videoJuegoRepositorioLocal.favoritos

    fun obtenerVideoJuegos() {
        screenModelScope.launch {
            mutableState.update {
                it.copy(estaCargando  = true)
            }

            videojuegoRepositorio.obtenerVideoJuegos()
                .collect { response ->
                    when (response) {
                        is ResultadoRed.Exitoso -> {

                            mutableState.update {
                                it.copy(
                                    videoJuegos = response.datos ?: emptyList(),
                                    estaCargando = false,
                                )
                            }

                        }

                        is ResultadoRed.Error -> {
                            mutableState.update {
                                it.copy(
                                    estaCargando = false,
                                    mensajeError = "${response.mensajeError} ${response.codigoError}"
                                )
                            }
                        }


                    }
                }
        }
    }
    /*fun obtenerVideoJuegosFavoritos()  {
        screenModelScope.launch {
            mutableState.update {
                it.copy(estaCargando  = true)
            }

            mutableState.update {
                val favoritosBD = videoJuegoRepositorioLocal.obtenerVideoJuegosFavoritos()
                it.copy(
                    videoJuegos = favoritosBD.value?.map { it.convertirADominio() } ?: emptyList(),
                    estaCargando = false,
                )
            }
        }
    }*/

    fun insertarFavorito(videoJuegoEntidad: VideoJuegoEntidad) {
        screenModelScope.launch {
            withContext(Dispatchers.IO) {
                videoJuegoRepositorioLocal.insertarVideoJuego(videoJuegoEntidad)
            }
        }

    }

    fun obtenerDetalleVideoJuego(id: Int) {
        screenModelScope.launch {
            mutableState.update {
                it.copy(estaCargando = true)
            }
            videojuegoRepositorio.obetnerDetalleVideoJuego(id)
                .collect { response ->

                    when (response) {
                        is ResultadoRed.Exitoso -> {

                            mutableState.update {
                                it.copy(
                                    detalleVideoJuego = response.datos, estaCargando = false,
                                )
                            }

                        }

                        is ResultadoRed.Error -> {
                            mutableState.update {
                                it.copy(
                                    estaCargando  = false,
                                    mensajeError = "${response.mensajeError} ${response.codigoError}"
                                )
                            }
                        }

                    }
                }
        }
    }
}