package com.advantys.el_cortijillo.Utils

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.databinding.SnackbarBinding
import com.google.android.material.snackbar.Snackbar
import java.io.File
import java.util.Date

fun Any?.esNulo() = this == null
//endregion

//region Activity

fun Activity.mostrarSnackbarPersonalizado(mensaje: String, icono: Int? = null, colorTexto: Int = R.color.black, colorFondo: Int = R.color.oscuro) {
    val contenedor = this.findViewById<View>(android.R.id.content)
    val snackView = View.inflate(this, R.layout.snackbar, null)
    val binding = SnackbarBinding.bind(snackView)
    val snackBar = Snackbar.make(contenedor, "", Snackbar.LENGTH_LONG)

    binding.txtMensaje.setTextColor(ContextCompat.getColor(this, colorTexto))
    binding.txtMensaje.text = mensaje

    if(icono.esNulo()) binding.imgImagen.visibility = View.GONE
    else binding.imgImagen.setImageResource(icono!!)

    val gradiente = binding.layoutRoot.background as GradientDrawable
    if(!colorFondo.esNulo()) gradiente.setColor(ContextCompat.getColor(this, colorFondo))

    snackBar.apply {
        (view as ViewGroup).addView(binding.root)
        setBackgroundTint(Color.TRANSPARENT)
        show()
    }
}

fun Activity.mostrarSnackbar(mensaje: String, tipoAlerta: TipoAlerta) {
    when (tipoAlerta) {
        TipoAlerta.ok -> this.mostrarSnackbarPersonalizado(mensaje, R.drawable.aceptar, R.color.white, R.color.ok)
        TipoAlerta.error -> this.mostrarSnackbarPersonalizado(mensaje, R.drawable.error, R.color.white, R.color.error)
        TipoAlerta.informacion -> this.mostrarSnackbarPersonalizado(mensaje, R.drawable.informacion, R.color.white, R.color.informacion)
        TipoAlerta.advertencia -> this.mostrarSnackbarPersonalizado(mensaje, R.drawable.advertencia,R.color.white, R.color.atencion)
    }
}
    fun Activity.showProgressDialog(message: String, delayMillis: Long): AlertDialog {
        val progressBar = ProgressBar(this)
        progressBar.isIndeterminate = false

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(progressBar)
        dialogBuilder.setMessage(message)
        dialogBuilder.setCancelable(false)

        val dialog = dialogBuilder.create()
        dialog.window?.setGravity(Gravity.CENTER)

        dialog.show()

        Handler(Looper.getMainLooper()).postDelayed({
            if (dialog.isShowing) {
                dialog.dismiss()
            }
        }, delayMillis)

        return dialog
    }

    fun Activity.passwordDialogo(message: String, onPasswordEntered: (String) -> Unit): AlertDialog {
        val input = EditText(this)
        input.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
        input.hint = " Contraseña"

        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(input)
        dialogBuilder.setMessage(message)
        dialogBuilder.setCancelable(false)

        dialogBuilder.setPositiveButton("Aceptar") { dialog, which ->
            val password = input.text.toString()
            onPasswordEntered(password)
        }

        dialogBuilder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = dialogBuilder.create()
        dialog.window?.setGravity(Gravity.CENTER)

        dialog.show()

        return dialog
    }

    fun Activity.showOkDialog(message: String): AlertDialog {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(message)
        dialogBuilder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = dialogBuilder.create()
        dialog.show()

        return dialog
    }

fun AppCompatActivity.actionBar(
    titulo: String,
    subtitulo: String? = null,
    displayHomeAsUpEnabled: Boolean = true,
    homeButtonEnabled: Boolean = true,
    backgroundResId: Int? = null // Agregamos el parámetro backgroundResId
) {
    supportActionBar?.apply {
        setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        setHomeButtonEnabled(homeButtonEnabled)
        this.title = titulo
        this.subtitle = subtitulo
        backgroundResId?.let {
            setBackgroundDrawable(ContextCompat.getDrawable(this@actionBar, it))
        }
    }
}

    //endregion

    //region File

    fun File.eliminarRecursivo(ruta: File): Boolean{
        return if (ruta.exists()) ruta.deleteRecursively()
        else false;
    }

    fun File.crearDirectorio(ruta: File): Boolean{
        return if (!ruta.exists()) ruta.mkdirs()
        else false;
    }

    fun File.eliminarArchivo(): Boolean{
        return if(this.exists()) this.delete()
        else false;
    }

    fun File.escribirTXT(contenido: String) {
        bufferedWriter().use { out ->
            out.write(contenido)
        }
    }

    fun File.obtenerArchivosEnDirectorio(): List<File> {
        if (!exists() || !isDirectory) return emptyList()
        return listFiles()?.toList() ?: emptyList()
    }

    fun File.obtenerFechaModificacion() = Date(lastModified())

    fun File.obtenerArchivosPorFechaModificacion(): List<File> {
        return if (!exists() || !isDirectory) emptyList()
        else listFiles()?.sortedByDescending { it.lastModified() } ?: emptyList()
    }

    fun File.escribirCSV(contenido: List<List<String>>, separador: String = ",") {
        bufferedWriter().use { out ->
            contenido.forEach { fila ->
                out.write(fila.joinToString(separador))
                out.newLine()
            }
        }
    }

    //endregion