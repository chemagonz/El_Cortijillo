package com.advantys.el_cortijillo.UI.Views.BocadillosPersonalizados

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Ingrediente
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.BocadillosPersonalizados_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.Utils.Respuesta
import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityIngredientesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BocadillosPersonalizados_Activity : AppCompatActivity() {

    private lateinit var binding : ActivityIngredientesBinding
    val bocadillosPerViewmodel: BocadillosPersonalizados_ViewModel by viewModels()
    val productoViewmodel: Productos_ViewModel by viewModels()

    private var ingredienteID: Int? = null

    //Instancias para guardar item

    private lateinit var sharedPreferences: SharedPreferences
    private val SHARED_PREFS_KEY = "IngredientesPref"
    private val KEY_SELECTED_INGREDIENTE1 = "Ingrediente1"
    private val KEY_SELECTED_INGREDIENTE2 = "Ingrediente2"
    private val KEY_SELECTED_INGREDIENTE3 = "Ingrediente3"
    private val KEY_SELECTED_INGREDIENTE4 = "Ingrediente4"
    private val KEY_SELECTED_INGREDIENTE5 = "Ingrediente5"



    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityIngredientesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE)

        actionBar("BOCADILLOS PERSONALIZADOS", backgroundResId = R.drawable.background_intro)

        mostrarIngredientes1()
        mostrarIngredientes2()
        mostrarIngredientes3()
        mostrarIngredientes4()
        mostrarIngredientes5()

        insertarBocadilloPersonalizado()

    }

    //Funcion para guardar items en los autocomplete.
    private fun guardarItemSeleccionados(itemSeleccionado: String, key:String) {
        val editor = sharedPreferences.edit()

        editor.putString(key, itemSeleccionado)
        editor.apply()
    }

    private fun mostrarIngredientes1() {
        bocadillosPerViewmodel.obtenerIngredientes()
        bocadillosPerViewmodel.ingredientesModel.observe(this, Observer{ ingredientes ->

            binding.autocomplete1.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ingredientes))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_INGREDIENTE1, "")
            val position = ingredientes.indexOfFirst { it?.nombre == selectedItem }

            if (position != -1) {
                binding.autocomplete1.setText(selectedItem, false)
                ingredienteID = ingredientes[position]?.id

            }
        })

        binding.autocomplete1.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Ingrediente

            Toast.makeText(applicationContext, "Código: ${elementoSeleccionado.id}, Nombre: ${elementoSeleccionado.nombre}", Toast.LENGTH_SHORT).show()

            ingredienteID = elementoSeleccionado.id
            elementoSeleccionado.nombre?.let { guardarItemSeleccionados(it, KEY_SELECTED_INGREDIENTE1) }
        }
    }

    private fun mostrarIngredientes2() {
        bocadillosPerViewmodel.obtenerIngredientes()
        bocadillosPerViewmodel.ingredientesModel.observe(this, Observer{ ingredientes ->

            binding.autocomplete2.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ingredientes))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_INGREDIENTE2, "")
            val position = ingredientes.indexOfFirst { it?.nombre == selectedItem }

            if (position != -1) {
                binding.autocomplete2.setText(selectedItem, false)
                ingredienteID = ingredientes[position]?.id

            }
        })

        binding.autocomplete2.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Ingrediente

            Toast.makeText(applicationContext, "Código: ${elementoSeleccionado.id}, Nombre: ${elementoSeleccionado.nombre}", Toast.LENGTH_SHORT).show()

            ingredienteID = elementoSeleccionado.id
            elementoSeleccionado.nombre?.let { guardarItemSeleccionados(it, KEY_SELECTED_INGREDIENTE2) }
        }
    }

    private fun mostrarIngredientes3() {
        bocadillosPerViewmodel.obtenerIngredientes()
        bocadillosPerViewmodel.ingredientesModel.observe(this, Observer{ ingredientes ->

            binding.autocomplete3.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ingredientes))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_INGREDIENTE3, "")
            val position = ingredientes.indexOfFirst { it?.nombre == selectedItem }

            if (position != -1) {
                binding.autocomplete3.setText(selectedItem, false)
                ingredienteID = ingredientes[position]?.id

            }
        })

        binding.autocomplete3.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Ingrediente

            Toast.makeText(applicationContext, "Código: ${elementoSeleccionado.id}, Nombre: ${elementoSeleccionado.nombre}", Toast.LENGTH_SHORT).show()

            ingredienteID = elementoSeleccionado.id
            elementoSeleccionado.nombre?.let { guardarItemSeleccionados(it, KEY_SELECTED_INGREDIENTE3) }
        }
    }

    private fun mostrarIngredientes4() {
        bocadillosPerViewmodel.obtenerIngredientes()
        bocadillosPerViewmodel.ingredientesModel.observe(this, Observer{ ingredientes ->

            binding.autocomplete4.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ingredientes))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_INGREDIENTE4, "")
            val position = ingredientes.indexOfFirst { it?.nombre == selectedItem }

            if (position != -1) {
                binding.autocomplete4.setText(selectedItem, false)
                ingredienteID = ingredientes[position]?.id

            }
        })

        binding.autocomplete4.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Ingrediente

            Toast.makeText(applicationContext, "Código: ${elementoSeleccionado.id}, Nombre: ${elementoSeleccionado.nombre}", Toast.LENGTH_SHORT).show()

            ingredienteID = elementoSeleccionado.id
            elementoSeleccionado.nombre?.let { guardarItemSeleccionados(it, KEY_SELECTED_INGREDIENTE4) }
        }
    }

    private fun mostrarIngredientes5() {
        bocadillosPerViewmodel.obtenerIngredientes()
        bocadillosPerViewmodel.ingredientesModel.observe(this, Observer{ ingredientes ->

            binding.autocomplete5.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, ingredientes))
            val selectedItem = sharedPreferences.getString(KEY_SELECTED_INGREDIENTE5, "")
            val position = ingredientes.indexOfFirst { it?.nombre == selectedItem }

            if (position != -1) {
                binding.autocomplete5.setText(selectedItem, false)
                ingredienteID = ingredientes[position]?.id

            }
        })

        binding.autocomplete5.setOnItemClickListener { parent, view, position, id ->
            val elementoSeleccionado = parent.getItemAtPosition(position) as Ingrediente

            Toast.makeText(applicationContext, "Código: ${elementoSeleccionado.id}, Nombre: ${elementoSeleccionado.nombre}", Toast.LENGTH_SHORT).show()

            ingredienteID = elementoSeleccionado.id
            elementoSeleccionado.nombre?.let { guardarItemSeleccionados(it, KEY_SELECTED_INGREDIENTE5) }
        }
    }

    private fun insertarBocadilloPersonalizado() {
        binding.button2.setOnClickListener {
            val usuarioID = intent.getIntExtra("USER_ID", -1)
            val nuevoBocadillo = BocadilloPersonalizado(
                nombre = binding.edNombreBocadillo.text.toString(),
                ingrediente1 = binding.autocomplete1.text.toString(),
                ingrediente2 = binding.autocomplete2.text.toString(),
                ingrediente3 = binding.autocomplete3.text.toString(),
                ingrediente4 = binding.autocomplete4.text.toString(),
                ingrediente5 = binding.autocomplete5.text.toString(),
                precio = 6.0,
                usuarioID = usuarioID
            )

            val bocadilloIdLiveData = bocadillosPerViewmodel.registroBocadilloPersonalizado(nuevoBocadillo)
            bocadilloIdLiveData.observe(this, Observer { bocadilloId ->
                if (bocadilloId != null) {
                    productoViewmodel.insertCestaItems(
                        usuarioID = usuarioID,
                        productoID = null,
                        bocadilloPersonalizadoID = bocadilloId.toInt(),
                        cantidad = 1,
                        nombreProducto = null,
                        descripcion = null,
                        precioProducto = null,
                        imagenProducto = null,
                        nombreBocadillo = nuevoBocadillo.nombre,
                        ingredientesBocadillo = null,
                        precioBocadillo = nuevoBocadillo.precio,
                        imagenBocadillo = null
                    )
                }
            })

            bocadillosPerViewmodel.mensajeRegistro.observe(this, Observer { mensaje ->
                when (mensaje.tipo) {
                    Respuesta.Tipo.OK -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.ok)
                    Respuesta.Tipo.ERROR -> mostrarSnackbar(mensaje.mensaje, TipoAlerta.error)
                    else -> Unit
                }
            })
        }
    }
}