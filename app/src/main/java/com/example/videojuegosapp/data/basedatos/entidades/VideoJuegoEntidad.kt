package com.example.videojuegosapp.data.basedatos.entidades

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "VideoJuego")
data class VideoJuegoEntidad(
                    @PrimaryKey
                    @ColumnInfo val id: Int,
                    @ColumnInfo val titulo: String,
                    @ColumnInfo val miniatura: String,
                    @ColumnInfo val descripcionCorta: String,
                    @ColumnInfo val desarrollador: String,
                    @ColumnInfo val genero: String,
                    @ColumnInfo val plataforma: String,
                    @ColumnInfo val editor: String,
                    @ColumnInfo val fechaLanzamiento: String
)