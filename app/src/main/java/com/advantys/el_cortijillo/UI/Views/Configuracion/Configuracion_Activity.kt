package com.advantys.el_cortijillo.UI.Views.Configuracion

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Usuario_ViewModel
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityConfiguracionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Configuracion_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityConfiguracionBinding
    val usuarioViewmodel: Usuario_ViewModel by viewModels()

    private var startTime: Long = 0
    private var totalTimeUsed: Long = 0
    private var usageTimeValueTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityConfiguracionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val userId = intent.getIntExtra("USER_ID", -1)
        usuarioViewmodel.obtenerUsuarioDetalles(userId)
        usuarioViewmodel.usuarioModel.observe(this, Observer { usuario ->
            usuario?.let { infoUsuario(usuario) }
        })

        actionBar("CONFIGURACIÓN",backgroundResId = R.drawable.background_intro)
        usageTimeValueTextView = binding.usageTimeValueTextView

        // Cargar tiempo acumulado desde SharedPreferences
        totalTimeUsed = getSharedPreferences("app_prefs", MODE_PRIVATE).getLong("totalTimeUsed", 0);

        // Actualizar la vista con el tiempo de uso
        updateUsageTimeTextView();
    }

    override fun onStart() {
        super.onStart()
        // Registrar el tiempo de inicio
        startTime = System.currentTimeMillis()
    }

    override fun onStop() {
        super.onStop()
        // Calcular el tiempo de uso actual y añadirlo al total
        val endTime = System.currentTimeMillis()
        totalTimeUsed += endTime - startTime

        // Guardar el tiempo acumulado en SharedPreferences
        getSharedPreferences("app_prefs", MODE_PRIVATE)
            .edit()
            .putLong("totalTimeUsed", totalTimeUsed)
            .apply()
    }

    private fun updateUsageTimeTextView() {
        // Convertir el tiempo de milisegundos a minutos y segundos
        val seconds = (totalTimeUsed / 1000).toInt() % 60
        val minutes = (totalTimeUsed / (1000 * 60) % 60).toInt()
        val hours = (totalTimeUsed / (1000 * 60 * 60) % 24).toInt()

        // Formatear el tiempo y mostrarlo
        val timeFormatted = String.format("%02d:%02d:%02d", hours, minutes, seconds)
        usageTimeValueTextView!!.text = timeFormatted
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun infoUsuario(usuario: Usuario?) {

        binding.edUsuario.setText(usuario?.nombre)
        binding.edTelefono.setText(usuario?.telefono)
        binding.edcorreo.setText(usuario?.correo)

    }

}