package com.example.videojuegosapp.data.repositorio

import com.example.videojuegosapp.data.ResultadoRed
import com.example.videojuegosapp.data.api.VideoJuegoApiServicio
import com.example.videojuegosapp.data.dto.DetalleVideoJuegoDTO
import com.example.videojuegosapp.data.invocacionApiSegura
import com.example.videojuegosapp.data.mapeador.convertirADominio
import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import com.example.videojuegosapp.dominio.modelo.VideoJuego
import com.example.videojuegosapp.dominio.repositorio.VideoJuegoRepositorio
import io.ktor.client.call.body
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class VideoJuegoRepositorioImpl(
private val videoJuegoApiServicio: VideoJuegoApiServicio
) : VideoJuegoRepositorio {

    override suspend fun obtenerVideoJuegos(): Flow<ResultadoRed<List<VideoJuego>>> = flow {
        val response = invocacionApiSegura {
            videoJuegoApiServicio.obtenerTodosLosVideoJuegos().map { it.convertirADominio() }
        }

        emit(response)

    }

    override suspend fun obetnerDetalleVideoJuego(id: Int): Flow<ResultadoRed<DetalleVideoJuego>> =
        flow {
            val response = invocacionApiSegura {
                videoJuegoApiServicio.obtenerDetalleVideoJuego(id).body<DetalleVideoJuegoDTO>().convertirADominio()
            }
            emit(response)
        }
}