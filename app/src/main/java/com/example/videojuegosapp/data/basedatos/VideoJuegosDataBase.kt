package com.example.videojuegosapp.data.basedatos

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.videojuegosapp.data.basedatos.dao.VideoJuegoDAO
import com.example.videojuegosapp.data.basedatos.entidades.VideoJuegoEntidad


const val NOMBRE_BASE_DATOS = "videojuegos_database"

@Database(entities = [VideoJuegoEntidad::class], version = 1)
abstract class VideoJuegosDataBase : RoomDatabase() {
    abstract fun videoJuegoDAO(): VideoJuegoDAO

    companion object {
        private var INSTANCE: VideoJuegosDataBase? = null
        fun getDatabase(context: Context): VideoJuegosDataBase? {
            if (INSTANCE == null) {
                synchronized(VideoJuegosDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(
                            context.applicationContext,
                            VideoJuegosDataBase::class.java, "videojuegos_database"
                        ) // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}
