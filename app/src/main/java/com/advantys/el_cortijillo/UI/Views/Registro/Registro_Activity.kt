package com.advantys.el_cortijillo.UI.Views.Registro

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Usuario_ViewModel
import com.advantys.el_cortijillo.UI.Views.Login.Login_Activity
import com.advantys.el_cortijillo.Utils.Respuesta
import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityRegistroBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Registro_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding
    val usuarioViewmodel: Usuario_ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        yaTengoCuenta()
        registroUsuario()
    }

    private fun registroUsuario() {

        binding.btnRegistrarse.setOnClickListener {
            val nuevoUsuario = Usuario(
                nombre = binding.nuevoUsuario.text.toString(),
                telefono = binding.edTelefono.text.toString(),
                correo = binding.email.text.toString(),
                password = binding.Password.text.toString()
            )

            if (binding.Password != binding.RPassword) {
                mostrarSnackbar("Las contraseñas no coinciden", TipoAlerta.error)
            }

            usuarioViewmodel.registroUsuario(nuevoUsuario)
            usuarioViewmodel.mensajeRegistro.observe(this,  Observer { mensaje ->
                when (mensaje.tipo) {
                    Respuesta.Tipo.OK -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.ok)
                    Respuesta.Tipo.ERROR -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.error)
                    else -> Unit
                }
            })
        }
    }
    private fun yaTengoCuenta() {
        binding.btnYaTengoCuenta.setOnClickListener {
            val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

}

