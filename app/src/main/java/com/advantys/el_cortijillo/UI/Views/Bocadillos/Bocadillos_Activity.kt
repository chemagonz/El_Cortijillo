package com.advantys.el_cortijillo.UI.Views.Bocadillos

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
import com.advantys.el_cortijillo.UI.ViewModels.Bocadillos_ViewModel
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityBienvenidaBinding
import com.advantys.el_cortijillo.databinding.ActivityBocadillosBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Bocadillos_Activity : AppCompatActivity() {

    val bocadillosViewmodel: Bocadillos_ViewModel by viewModels()
    private lateinit var binding : ActivityBocadillosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityBocadillosBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("BOCADILLOS")
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
        bocadillosViewmodel.obtenerBocadillos()
        bocadillosViewmodel.bocadillosModel.observe(this, Observer {
            binding.recyclerviewBocadillos.layoutManager= LinearLayoutManager(this)
            binding.recyclerviewBocadillos.adapter = Bocadillos_Adapter(it, bocadillosViewmodel)
        })
    }

}