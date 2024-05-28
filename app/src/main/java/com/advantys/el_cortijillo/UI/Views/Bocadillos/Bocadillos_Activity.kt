package com.advantys.el_cortijillo.UI.Views.Bocadillos

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Bocadillos_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.busquedaProductos_Adapter
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityBocadillosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Bocadillos_Activity : AppCompatActivity() {

    val bocadillosViewmodel: Bocadillos_ViewModel by viewModels()
    val productosViewmodel: Productos_ViewModel by viewModels()

    private lateinit var binding : ActivityBocadillosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBocadillosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("BOCADILLOS", backgroundResId = R.drawable.background_intro)
        mostrarBocadillos()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun mostrarBocadillos(){
        val userID = intent.getIntExtra("USER_ID", -1)
        val bocadilloCategoria = intent.getStringExtra("categoria")
        productosViewmodel.obtenerProductosCategoria(bocadilloCategoria)
        productosViewmodel.productoModel.observe(this, Observer {
            binding.recyclerviewBocadillos.layoutManager= LinearLayoutManager(this)
            binding.recyclerviewBocadillos.adapter = busquedaProductos_Adapter(it, productosViewmodel, userID)
        })
    }
}