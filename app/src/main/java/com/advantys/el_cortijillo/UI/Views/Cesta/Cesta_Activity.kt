package com.advantys.el_cortijillo.UI.Views.Cesta

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.databinding.ActivityCestaBinding

class Cesta_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityCestaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCestaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        actionBar("CESTA", backgroundResId = R.drawable.background_intro)
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