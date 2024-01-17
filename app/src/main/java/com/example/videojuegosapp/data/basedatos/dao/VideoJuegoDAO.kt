package com.example.videojuegosapp.data.basedatos.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.videojuegosapp.data.basedatos.entidades.VideoJuegoEntidad

@Dao
interface VideoJuegoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarVideoJuego(videoJuego: VideoJuegoEntidad)

    @Update
    suspend fun actualizarVideoJuego(videoJuego: VideoJuegoEntidad)

    @Delete
    suspend fun eliminarVideoJuego(videoJuego: VideoJuegoEntidad)

    @Query("SELECT * FROM videojuego ORDER BY fechaLanzamiento DESC")
    fun obtenerVideoJuegosFavoritos(): LiveData<List<VideoJuegoEntidad>>

    @Query("DELETE FROM videojuego")
    suspend fun clearNote()

    @Query("DELETE FROM videojuego WHERE id = :id")
    suspend fun eliminarVideoJuegoPorId(id: Int)
}