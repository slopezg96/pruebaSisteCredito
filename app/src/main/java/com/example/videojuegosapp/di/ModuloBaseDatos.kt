package com.example.videojuegosapp.di

import android.content.Context
import androidx.room.Room
import com.example.videojuegosapp.data.basedatos.VideoJuegosDataBase

fun provideDataBase(context: Context) =
    Room.databaseBuilder(context, VideoJuegosDataBase::class.java, "VIDEOJUEGOS_DATABASE")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()


fun provideDAO(dataBase: VideoJuegosDataBase) = dataBase.videoJuegoDAO()