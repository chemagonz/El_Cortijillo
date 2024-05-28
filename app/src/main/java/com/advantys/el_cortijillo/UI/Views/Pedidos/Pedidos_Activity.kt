package com.advantys.el_cortijillo.UI.Views.Pedidos

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
import com.advantys.el_cortijillo.UI.ViewModels.Pedidos_ViewModel
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.busquedaProductos_Adapter
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityPedidosBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class Pedidos_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityPedidosBinding

    private lateinit var pedidosAdapter: Pedidos_Adapter
    val pedidoViewmodel: Pedidos_ViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPedidosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("PEDIDOS", backgroundResId = R.drawable.background_intro)
        mostrarPedidos()
    }

    fun mostrarPedidos() {

        val userId = intent.getIntExtra("USER_ID", -1)
        pedidoViewmodel.obtenerPedidos(userId)
        pedidoViewmodel.pedidoModel.observe(this, Observer {
            binding.recyclerviewPedidos.layoutManager = LinearLayoutManager(this)
            binding.recyclerviewPedidos.adapter = Pedidos_Adapter(it, pedidoViewmodel, userId)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }
}