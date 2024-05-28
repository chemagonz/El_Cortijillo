package com.advantys.el_cortijillo.UI.Views.Hamburguesas

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Hamburguesas_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.busquedaProductos_Adapter
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityHamburguesasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Hamburguesas_Activity : AppCompatActivity() {

    val hamburguesasViewmodel: Hamburguesas_ViewModel by viewModels()
    val productosViewmodel: Productos_ViewModel by viewModels()

    private lateinit var binding : ActivityHamburguesasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHamburguesasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("HAMBURGUESAS",backgroundResId = R.drawable.background_intro)
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
        val userID = intent.getIntExtra("USER_ID", -1)
        val hamburguesaCategoria = intent.getStringExtra("categoria")
        productosViewmodel.obtenerProductosCategoria(hamburguesaCategoria)
        productosViewmodel.productoModel.observe(this, Observer {
            binding.recyclerviewHamburguesas.layoutManager= LinearLayoutManager(this)
            binding.recyclerviewHamburguesas.adapter = busquedaProductos_Adapter(it, productosViewmodel, userID)
        })
    }


}