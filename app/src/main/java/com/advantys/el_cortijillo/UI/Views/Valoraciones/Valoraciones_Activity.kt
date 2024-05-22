package com.advantys.el_cortijillo.UI.Views.Valoraciones

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.databinding.ActivityValoracionesBinding

class Valoraciones_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityValoracionesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityValoracionesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}