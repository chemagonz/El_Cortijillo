package com.advantys.el_cortijillo.UI.Views.Bienvenida

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.Views.Login.Login_Activity
import com.advantys.el_cortijillo.UI.Views.Registro.Registro_Activity
import com.advantys.el_cortijillo.databinding.ActivityBienvenidaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Bienvenida_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityBienvenidaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBienvenidaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.irInicioSesion.setOnClickListener {
            val intent =  Intent (this, Login_Activity::class.java )
            startActivity(intent)
            finish()
        }


        binding.irRegistro.setOnClickListener {
            val intent =  Intent (this, Registro_Activity::class.java )
            startActivity(intent)
            finish()
        }
    }
}