package com.advantys.el_cortijillo.UI.Views.Productos

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.el_cortijillo.Domain.Models.Producto
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.Utils.Respuesta
import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityDetallesProductosBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductoDetalles_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityDetallesProductosBinding
    val productosViewmodel: Productos_ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetallesProductosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("DETALLES", backgroundResId = R.drawable.background_intro)
        mostrarDetalles()
        insertarProductosCesta()


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {

                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun mostrarDetalles() {

        val detallesID = intent.getIntExtra("id", 0)
        val categoria = intent.getStringExtra("categoria")

        productosViewmodel.obtenerProductoDetalle(categoria,detallesID)
        productosViewmodel.productoModelDet.observe(this, Observer { producto ->
            producto?.let { verDetallesProducto(producto) }
        })

    }

    private fun insertarProductosCesta() {
        val usuarioID = intent.getIntExtra("USER_ID",-1)
        val productoID = intent.getIntExtra("productoID", -1)
        val nombre = intent.getStringExtra("nombre")
        val descripcion = intent.getStringExtra("descripcion")
        val precio = intent.getDoubleExtra("precio", -1.0)
        val imagen = intent.getStringExtra("imagen")

        binding.insertarACesta.setOnClickListener {
            productosViewmodel.insertCestaItems(usuarioID, productoID, null, 1, nombre, descripcion,precio,imagen, null, null, null, null)
            productosViewmodel.mensajeRegistro.observe(this, Observer { mensaje ->
                when (mensaje.tipo) {
                    Respuesta.Tipo.OK -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.ok)
                    Respuesta.Tipo.ERROR -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.error)
                    else -> Unit
                }
            })
        }

    }

    private fun verDetallesProducto(producto: Producto) {

        binding.nombre.text = producto.nombre
        binding.precio.text = producto.precio.toString()
        binding.descripcion.text = producto.descripcion

      Glide.with(this)
          .load(producto.imagen)
          .into(binding.imagen)
    }
}