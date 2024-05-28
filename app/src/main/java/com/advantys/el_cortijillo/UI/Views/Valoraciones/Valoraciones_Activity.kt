package com.advantys.el_cortijillo.UI.Views.Valoraciones

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.Domain.Models.Valoracion
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Pedidos_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Valoraciones_ViewModel
import com.advantys.el_cortijillo.UI.Views.Pedidos.Pedidos_Adapter
import com.advantys.el_cortijillo.Utils.Respuesta
import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityValoracionesBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class Valoraciones_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityValoracionesBinding
    val valoracionesViewmodel: Valoraciones_ViewModel by viewModels()
    private lateinit var valoracionesAdapter: Valoraciones_Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityValoracionesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("VALORACIONES", backgroundResId = R.drawable.background_intro)

        setupRecyclerView()
        insertarValoraciones()
        observarValoraciones()
    }

    private fun setupRecyclerView() {
        valoracionesAdapter = Valoraciones_Adapter(emptyList(), valoracionesViewmodel)
        binding.recyclerViewValoraciones.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewValoraciones.adapter = valoracionesAdapter
    }

    private fun insertarValoraciones() {
        val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val userID = intent.getIntExtra("USER_ID", -1)
        val nombre = intent.getStringExtra("nombre")

        binding.insertarValoracion.setOnClickListener {
            val nuevaValoracion = Valoracion(
                usuarioID = userID,
                valoracion = binding.edValoracion.text.toString(),
                fecha = fecha,
                nombreUsuario = nombre
            )

            if (binding.edValoracion.text.isNotEmpty()) {
                valoracionesViewmodel.registroValoracion(nuevaValoracion)
                binding.edValoracion.text.clear() // Limpiar el campo de entrada después de añadir la valoración
            } else {
                mostrarSnackbar("Debe rellenar el campo de valoración", TipoAlerta.advertencia)
            }
        }

        valoracionesViewmodel.mensajeRegistro.observe(this, Observer { mensaje ->
            when (mensaje.tipo) {
                Respuesta.Tipo.OK -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.ok)
                Respuesta.Tipo.ERROR -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.error)
                else -> Unit
            }
        })
    }

    private fun observarValoraciones() {
        valoracionesViewmodel.valoracionModel.observe(this, Observer { valoraciones ->
            valoracionesAdapter.updateData(valoraciones)
        })

        valoracionesViewmodel.obtenerValoraciones()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}