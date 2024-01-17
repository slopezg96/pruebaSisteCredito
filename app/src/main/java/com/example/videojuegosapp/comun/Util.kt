package com.example.videojuegosapp.comun

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
internal fun String.convertirAFormatoMostrarFecha() : String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val fecha = LocalDate.parse(this, formatter)
    return  fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}