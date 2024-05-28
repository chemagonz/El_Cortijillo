package com.advantys.el_cortijillo.UI.Views.Cesta

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Cesta_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Pedidos_ViewModel
import com.advantys.el_cortijillo.Utils.Respuesta
import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityCestaBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class Cesta_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCestaBinding
    val cestaViewmodel: Cesta_ViewModel by viewModels()
    val pedidoViewmodel: Pedidos_ViewModel by viewModels()
    private lateinit var cestaAdapter: Cesta_Adapter
    private var total: String = 0.0.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCestaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("CESTA", backgroundResId = R.drawable.background_intro)
        val userID = intent.getIntExtra("USER_ID", -1)

        setupRecyclerView()
        observeCesta(userID)
        realizarPedido()
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView() {
        cestaAdapter = Cesta_Adapter(mutableListOf(), cestaViewmodel)
        binding.recyclerViewCesta.apply {
            layoutManager = LinearLayoutManager(this@Cesta_Activity)
            adapter = cestaAdapter
        }
    }

    private fun observeCesta(userID: Int) {
        cestaViewmodel.obtenerItemsCesta(userID)
        cestaViewmodel.cestaModel.observe(this, Observer { cestaList ->
            cestaAdapter.updateData(cestaList)
            calcularTotal()
        })
    }

    private fun calcularTotal() {
        total = cestaAdapter.calcularTotal()
        binding.TOTALID.text = "$total €"
    }

     fun realizarPedido() {

         val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
         val horaRecogida = binding.edFechaRecogida.text
         val userID = intent.getIntExtra("USER_ID", -1)



         binding.realizarPedido.setOnClickListener {
             val nuevoPedido = Pedido(
                 usuarioID = userID,
                 total = total,
                 fecha = fecha.toString(),
                 horaRecogida = horaRecogida.toString()

             )

             if(horaRecogida.isNotEmpty()) {
                 pedidoViewmodel.registroPedido(nuevoPedido)
                 cestaViewmodel.deleteCesta()
             } else {
                 mostrarSnackbar("Debe indicar su hora de recogida.", TipoAlerta.advertencia)
             }


             pedidoViewmodel.mensajeRegistro.observe(this, Observer { mensaje ->
                 when (mensaje.tipo) {
                     Respuesta.Tipo.OK -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.ok)
                     Respuesta.Tipo.ERROR -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.error)
                     else -> Unit
                 }
             })
         }

    }
}