package com.example.videojuegosapp.ui.videojuegos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videojuegosapp.data.repositorio.VideoJuegoRepositorioImpl
import kotlinx.coroutines.launch
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.example.videojuegosapp.data.ResultadoRed
import com.example.videojuegosapp.ui.videojuegos.state.VideoJuegoState
import kotlinx.coroutines.flow.update


class VideoJuegoViewModel(
    private val videojuegoRepositorio: VideoJuegoRepositorioImpl
) : StateScreenModel<VideoJuegoState>(VideoJuegoState()) {

    init {
        obtenerVideoJuegos()
    }

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
                                    videoJuegos = response.datos ?: emptyList(), estaCargando = false,
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
}