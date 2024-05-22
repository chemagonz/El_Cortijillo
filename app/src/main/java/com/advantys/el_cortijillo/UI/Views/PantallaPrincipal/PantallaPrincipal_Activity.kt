package com.advantys.el_cortijillo.UI.Views.PantallaPrincipal

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Usuario_ViewModel
import com.advantys.el_cortijillo.UI.Views.Bocadillos.Bocadillos_Activity
import com.advantys.el_cortijillo.UI.Views.Cesta.Cesta_Activity
import com.advantys.el_cortijillo.UI.Views.Configuracion.Configuracion_Activity
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.ui.home.HomeFragment
import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityPantallaPrincipalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PantallaPrincipal_Activity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityPantallaPrincipalBinding
    val usuarioViewmodel: Usuario_ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el ID del usuario del Intent
        val userId = intent.getIntExtra("USER_ID", -1)

        // Navegar al HomeFragment pasando el ID del usuario
        if (savedInstanceState == null) {
            val homeFragment = HomeFragment.newInstance(userId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_pantalla_principal, homeFragment)
                .commit()
        }

        setSupportActionBar(binding.appBarPantallaPrincipal.toolbar)

        binding.appBarPantallaPrincipal.fab.setOnClickListener {
            val intent = Intent(this, Cesta_Activity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_pantalla_principal)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pantalla_principal_, menu)

        val configItem= menu.findItem(R.id.configuracion)

            configItem?.setOnMenuItemClickListener {

                val userId = intent.getIntExtra("USER_ID", -1)
                val intent = Intent(this, Configuracion_Activity::class.java).apply {
                    putExtra("USER_ID", userId)
                }
                startActivity(intent)
                true
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_pantalla_principal)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}