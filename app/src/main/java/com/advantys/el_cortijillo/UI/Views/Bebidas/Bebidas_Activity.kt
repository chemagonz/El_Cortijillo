package com.advantys.el_cortijillo.UI.Views.Bebidas

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Bebidas_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.busquedaProductos_Adapter
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityBebidasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Bebidas_Activity : AppCompatActivity() {

    val bebidasViewModel: Bebidas_ViewModel by viewModels()
    val productosViewmodel: Productos_ViewModel by viewModels()
    private lateinit var binding : ActivityBebidasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBebidasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("BEBIDAS",backgroundResId = R.drawable.background_intro)
        mostrarBebidas()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun mostrarBebidas(){
        val userID = intent.getIntExtra("USER_ID", -1)
        val bebidaCategoria = intent.getStringExtra("categoria")
        productosViewmodel.obtenerProductosCategoria(bebidaCategoria)
        productosViewmodel.productoModel.observe(this, Observer {
            binding.recyclerviewBebidas.layoutManager= LinearLayoutManager(this)
            binding.recyclerviewBebidas.adapter = busquedaProductos_Adapter(it, productosViewmodel , userID)
        })
    }
}