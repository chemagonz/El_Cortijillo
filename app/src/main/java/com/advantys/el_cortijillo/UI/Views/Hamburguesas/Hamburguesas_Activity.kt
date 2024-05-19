package com.advantys.el_cortijillo.UI.Views.Hamburguesas

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Bebidas_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Hamburguesas_ViewModel
import com.advantys.el_cortijillo.UI.Views.Bocadillos.Bocadillos_Adapter
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityHamburguesasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Hamburguesas_Activity : AppCompatActivity() {

    val hamburguesasViewmodel: Hamburguesas_ViewModel by viewModels()
    private lateinit var binding : ActivityHamburguesasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHamburguesasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("HAMBURGUESAS")
        mostrarHamburguesas()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun mostrarHamburguesas(){
        hamburguesasViewmodel.obtenerHamburguesas()
        hamburguesasViewmodel.hamburguesasModel.observe(this, Observer {
            binding.recyclerviewHamburguesas.layoutManager= LinearLayoutManager(this)
            binding.recyclerviewHamburguesas.adapter = Hamburguesas_Adapter(it, hamburguesasViewmodel)
        })
    }
}