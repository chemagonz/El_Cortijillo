package com.advantys.el_cortijillo.UI.Views.Login

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Usuario_ViewModel
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.PantallaPrincipal_Activity
import com.advantys.el_cortijillo.UI.Views.Registro.Registro_Activity
import com.advantys.el_cortijillo.Utils.Respuesta
import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityBienvenidaBinding
import com.advantys.el_cortijillo.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Login_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    val usuarioViewmodel: Usuario_ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.noTengoCuenta.setOnClickListener {
            val intent = Intent(this, Registro_Activity::class.java)
            startActivity(intent)
            finish()
        }

        usuarioViewmodel.userId.observe(this, Observer { userId ->
            userId?.let {

                val intent = Intent(this, PantallaPrincipal_Activity::class.java).apply {
                    putExtra("USER_ID", it)
                }
                startActivity(intent)
                finish()
            } ?: run {
                mostrarSnackbar("Credenciales incorrectas", TipoAlerta.error)
            }
        })

        binding.inicioSesion.setOnClickListener {
            val usuario = binding.edUsuario.text.toString()
            val password = binding.edPassword.text.toString()

           usuarioViewmodel.verificarUsuario(usuario,password)
        }

    }
}