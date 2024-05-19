package com.advantys.el_cortijillo.UI.Views.SplashScreen

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.advantys.el_cortijillo.UI.Views.Bienvenida.Bienvenida_Activity
import com.advantys.el_cortijillo.Utils.FechaHoy
import com.advantys.el_cortijillo.Utils.INICIO
import com.advantys.el_cortijillo.Utils.PermisosUtils
import com.advantys.el_cortijillo.Utils.Ruta
import com.advantys.el_cortijillo.Utils.crearBackup
import com.advantys.el_cortijillo.databinding.ActivitySplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Date

@AndroidEntryPoint
class SplashScreen_Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        //Verificar permisos
        Handler(Looper.getMainLooper()).postDelayed({
            // Verificar permisos
            if (!PermisosUtils.verificarPermisosAlmacenamiento(this)) {
                PermisosUtils.solicitarPermisosAlmacenamiento(this)
            } else {
                continuarDespuesPermisos()
            }
        }, 500)


    }

    private fun ObtenerRuta(): Boolean {
        var ok: Boolean = true
        //Se comprueba la ruta de la aplicación
        val file = File(Environment.getExternalStorageDirectory().path + "/ElCortijillo")
        if (!file.exists())
            File(Environment.getExternalStorageDirectory().path + "/ElCortijillo/").mkdir()

        val filea = File(Ruta, "Cortijillo.db")
        if (!filea.exists()) {
            mostrarDialogoBDNoDisponible()
            ok = false
        }

        return ok
    }


    //Si no tiene los permisos, salta aviso para permitir o denegar.
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == PermisosUtils.REQUEST_STORAGE_PERMISSION) {
            if(grantResults.isNotEmpty()){
                var flag = true

                for (resultado in grantResults) {
                    if (resultado == PackageManager.PERMISSION_DENIED) flag = false
                }

                if(flag) continuarDespuesPermisos()
                else mostrarDialogoPermisosDenegados()
            }
            else mostrarDialogoPermisosDenegados()
        }
    }
    private fun continuarDespuesPermisos() {
        val file = File(Ruta, "Cortijillo.db")
        if (!file.exists()) {
            GenerarBBDD()
        } else {
                ProcesoInicio()
        }
    }

    fun ProcesoInicio() {
        //Si la ruta y base de datos son correctas
        if (ObtenerRuta()) {
            FechaHoy = Date()
            //Si la ruta y base de datos son correctas
            if (ObtenerRuta()) {
                EntrarAlPrograma()
            }
        }
    }

    private fun GenerarBBDD() {
        File(Ruta + "Cortijillo.db").delete()
    }

    fun EntrarAlPrograma() {
        crearBackup(INICIO)
        val intent = Intent(this, Bienvenida_Activity::class.java)
        startActivity(intent)
        finish()
    }

    private fun mostrarDialogoBDNoDisponible() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Base de datos no encontrada")
            .setPositiveButton("Aceptar") { dialog, which ->
                GenerarBBDD()
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

    private fun mostrarDialogoPermisosDenegados() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Permisos denegados")
            .setMessage("Para continuar, por favor, concede los permisos de almacenamiento en la configuración de la aplicación.")
            .setPositiveButton("Configuración") { dialog, which ->
                PermisosUtils.irConfiguracionPermisos(this)
            }
            .setNegativeButton("Cancelar") { dialog, which ->
                finish()
            }
            .setCancelable(false)
            .show()
    }

}


