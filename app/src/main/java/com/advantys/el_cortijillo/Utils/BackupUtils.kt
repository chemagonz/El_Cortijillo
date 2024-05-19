package com.advantys.el_cortijillo.Utils

import android.os.Build
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream
import java.nio.file.Files
import java.text.SimpleDateFormat
import java.util.Calendar



const val INICIO = 0
    fun crearBackup(tipoCopia: Int) {
        try {
            val formatoDia = SimpleDateFormat("dd-MM-yyyy")
            val formatoHora = SimpleDateFormat("HH:mm:ss")
            val fecha: String = formatoDia.format(Calendar.getInstance().getTime())
            val hora: String = formatoHora.format(Calendar.getInstance().getTime())
            val directorioBackups: File = File(Ruta + "/Backups")
            val directorioDia = File("$directorioBackups/$fecha")
            var existeBackups = true
            var existeDia = true
            if (!directorioBackups.exists()) existeBackups = directorioBackups.mkdir()
            if (!directorioDia.exists()) existeDia = directorioDia.mkdir()
            if (existeBackups && existeDia) {
                val nombreBackup =
                    "/" + "ElCortijillo_" + tipoCopia(tipoCopia) + "_" + hora.replace(
                        ":",
                        "."
                    ) + ".db"
                val bdoriginal: File = File(Ruta + "/Cortijillo.db")
                val backup = File(directorioDia.toString() + nombreBackup)
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Files.copy(bdoriginal.toPath(), backup.toPath()) else {
                        if (backup.createNewFile()) {
                            val entrada: InputStream = FileInputStream(bdoriginal)
                            val salida: OutputStream = FileOutputStream(backup)
                            val buf = ByteArray(1024)
                            var longitud: Int
                            while (entrada.read(buf).also { longitud = it } > 0) {
                                salida.write(buf, 0, longitud)
                            }
                            entrada.close()
                            salida.close()
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun tipoCopia(tipoCopia: Int): String {
        return when (tipoCopia) {
            INICIO -> "Inicio"
            else -> ""
        }
    }


