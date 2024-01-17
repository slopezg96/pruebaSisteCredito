package com.example.videojuegosapp.data.mapeador

import com.example.videojuegosapp.data.basedatos.entidades.VideoJuegoEntidad
import com.example.videojuegosapp.data.dto.CapturaPantallaDTO
import com.example.videojuegosapp.data.dto.DetalleVideoJuegoDTO
import com.example.videojuegosapp.data.dto.RequerimientosMinimosSistemaDTO
import com.example.videojuegosapp.data.dto.VideoJuegoDTO
import com.example.videojuegosapp.dominio.modelo.CapturaPantalla
import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import com.example.videojuegosapp.dominio.modelo.RequerimientosMinimosSistema
import com.example.videojuegosapp.dominio.modelo.VideoJuego

internal fun RequerimientosMinimosSistemaDTO.convertirADominio(): RequerimientosMinimosSistema {
    return RequerimientosMinimosSistema(
        this.graficos,
        this.memoria,
        this.so,
        this.procesador,
        this.almacenamiento
    )
}

internal fun CapturaPantallaDTO.convertirADominio(): CapturaPantalla {
    return CapturaPantalla(
        this.id,
        this.imagen
    )
}

internal fun VideoJuegoDTO.convertirADominio(): VideoJuego{
    return VideoJuego(
        this.miniatura,
        this.titulo,
        this.genero,
        this.desarrollador,
        this.descripcionCorta,
        this.plataforma,
        this.editor,
        this.fechaLanzamiento,
        this.id)
}

internal fun VideoJuegoEntidad.convertirADominio(): VideoJuego{
    return VideoJuego(
        this.miniatura,
        this.titulo,
        this.genero,
        this.desarrollador,
        this.descripcionCorta,
        this.plataforma,
        this.editor,
        this.fechaLanzamiento,
        this.id)
}

internal fun VideoJuego.convertirAEntidad(): VideoJuegoEntidad{
    return VideoJuegoEntidad(
        this.id,
        this.titulo,
        this.miniatura,
        this.descripcionCorta,
        this.desarrollador,
        this.genero,
        this.plataforma,
        this.editor,
        this.fechaLanzamiento,
    )
}

internal fun DetalleVideoJuegoDTO.convertirADominio(): DetalleVideoJuego {
    return DetalleVideoJuego(
        this.titulo,
        this.miniatura,
        this.descripcionCorta,
        this.descripcion,
        this.fechaLanzamiento,
        this.genero,
        this.desarrollador,
        this.urlPerfilFreeToGame,
        this.urlVideoJuego,
        this.id,
        this.requerimientosMinimosSistema.convertirADominio(),
        this.plataforma,
        this.editor,
        this.capturasPantalla.map { it.convertirADominio() },
        this.estado
    )
}