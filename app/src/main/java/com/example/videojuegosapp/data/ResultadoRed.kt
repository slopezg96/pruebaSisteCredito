package com.example.videojuegosapp.data

import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException

sealed class ResultadoRed<T>(
    val datos: T? = null,
    val codigoError: Int? = null,
    val mensajeError: String? = null
) {
    class Exitoso<T>(data: T) : ResultadoRed<T>(data)
    class Error<T>(codigoError: Int, mensajeError: String?) :
        ResultadoRed<T>(codigoError = codigoError, mensajeError = mensajeError)
}

suspend fun <T : Any> invocacionApiSegura(invocaacionApi: suspend () -> T): ResultadoRed<T> {
    return try {
        ResultadoRed.Exitoso(data = invocaacionApi.invoke())

    } catch (e: RedirectResponseException) {

        ResultadoRed.Error(
            codigoError = e.response.status.value,
            mensajeError = e.message,
        )
    } catch (e: ClientRequestException) {

        ResultadoRed.Error(
            codigoError = e.response.status.value,
            mensajeError = e.message,
        )
    } catch (e: ServerResponseException) {

        ResultadoRed.Error(
            codigoError = e.response.status.value,
            mensajeError = e.message,
        )
    } catch (e: Exception) {
        ResultadoRed.Error(
            codigoError = 0,
            mensajeError = e.message ?: "Ha ocurrido un error",
        )
    }
}