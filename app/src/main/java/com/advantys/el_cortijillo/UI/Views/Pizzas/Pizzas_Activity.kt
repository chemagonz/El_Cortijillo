package com.advantys.el_cortijillo.UI.Views.Pizzas

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
import com.advantys.el_cortijillo.UI.ViewModels.Hamburguesas_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Pizzas_ViewModel
import com.advantys.el_cortijillo.UI.Views.Bocadillos.Bocadillos_Adapter
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityPizzasBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Pizzas_Activity : AppCompatActivity() {

    val pizzasViewmodel: Pizzas_ViewModel by viewModels()
    private lateinit var binding: ActivityPizzasBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPizzasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        actionBar("PIZZAS")
        mostrarPizzas()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun mostrarPizzas(){
        pizzasViewmodel.obtenerPizzas()
        pizzasViewmodel.pizzasModel.observe(this, Observer {
            binding.recyclerviewPizzas.layoutManager= LinearLayoutManager(this)
            binding.recyclerviewPizzas.adapter = Pizzas_Adapter(it, pizzasViewmodel)
        })
    }
}