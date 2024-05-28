package com.advantys.el_cortijillo.UI.Views.Informacion

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityInformacionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Informacion_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityInformacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInformacionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)

        setContentView(binding.root)


        actionBar("INFORMACIÓN", backgroundResId = R.drawable.background_intro)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}