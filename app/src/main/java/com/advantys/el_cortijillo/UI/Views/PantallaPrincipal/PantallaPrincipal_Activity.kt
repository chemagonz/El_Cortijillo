package com.advantys.el_cortijillo.UI.Views.PantallaPrincipal

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.R.string.navigation_drawer_close
import com.advantys.el_cortijillo.R.string.navigation_drawer_open
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Usuario_ViewModel
import com.advantys.el_cortijillo.UI.Views.Bebidas.Bebidas_Activity
import com.advantys.el_cortijillo.UI.Views.Bienvenida.Bienvenida_Activity
import com.advantys.el_cortijillo.UI.Views.Bocadillos.Bocadillos_Activity
import com.advantys.el_cortijillo.UI.Views.BocadillosPersonalizados.BocadillosPersonalizados_Activity
import com.advantys.el_cortijillo.UI.Views.Cesta.Cesta_Activity
import com.advantys.el_cortijillo.UI.Views.Configuracion.Configuracion_Activity
import com.advantys.el_cortijillo.UI.Views.Hamburguesas.Hamburguesas_Activity
import com.advantys.el_cortijillo.UI.Views.Informacion.Informacion_Activity
import com.advantys.el_cortijillo.UI.Views.Pedidos.Pedidos_Activity
import com.advantys.el_cortijillo.UI.Views.Pizzas.Pizzas_Activity
import com.advantys.el_cortijillo.UI.Views.Valoraciones.Valoraciones_Activity

import com.advantys.el_cortijillo.Utils.TipoAlerta
import com.advantys.el_cortijillo.Utils.actionBar
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ActivityPantallaPrincipalBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PantallaPrincipal_Activity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityPantallaPrincipalBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var busquedaProductosAdapter: busquedaProductos_Adapter
    val usuarioViewmodel: Usuario_ViewModel by viewModels()
    val productosviewModel: Productos_ViewModel by viewModels()
    private var nombreUsuario: String? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPantallaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el ID del usuario del Intent
        val userId = intent.getIntExtra("USER_ID", -1)

        usuarioViewmodel.obtenerUsuarioDetalles(userId)

        drawerLayout = binding.drawerLayout
        toggle = ActionBarDrawerToggle(this, drawerLayout, navigation_drawer_open, navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        actionBar("HOME", backgroundResId = R.drawable.background_intro)

        val navView: NavigationView = binding.navView
        navView.setNavigationItemSelectedListener(this)

        binding.root.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    val outRect = Rect()
                    navView.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                        drawerLayout.closeDrawer(GravityCompat.START)
                    }
                }
            }
            true
        }

        busquedaProductos()



        busquedaProductosAdapter = busquedaProductos_Adapter(emptyList(), productosviewModel, userId)
        binding.recyclerViewProductos.adapter = busquedaProductosAdapter
        binding.recyclerViewProductos.layoutManager = LinearLayoutManager(this)

        irBocadillos("Bocadillo",userId)
        irPizzas("Pizza",userId)
        irHamburguesas("Hamburguesa",userId)
        irBebidas("Bebida",userId)
        irBocadillosPersonalizados(userId)

        // Observa los cambios en el nombre del usuario
        usuarioViewmodel.usuarioModel.observe(this, Observer { nombre ->
            nombre?.let {
                binding.welcome.text = "Bienvenido ${nombre.nombre} !"
                nombreUsuario = it.nombre
            }
        })

        irCesta()
        irInformacion()

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.pantalla_principal_, menu)

        val configItem= menu.findItem(R.id.configuracion)
        val cerrarSesion = menu.findItem(R.id.salir)

            configItem?.setOnMenuItemClickListener {

                val userId = intent.getIntExtra("USER_ID", -1)
                val intent = Intent(this, Configuracion_Activity::class.java).apply {
                    putExtra("USER_ID", userId)
                }
                startActivity(intent)
                true
        }
            cerrarSesion.setOnMenuItemClickListener {
                val intent = Intent(this, Bienvenida_Activity::class.java)
                startActivity(intent)
                finish()
                true
            }
        return true
    }

    private fun busquedaProductos() {

        binding.busquedaProductosED.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if (query.isNotEmpty()) {
                    productosviewModel.obtenerProducto(query)
                    binding.recyclerViewProductos.visibility = View.VISIBLE
                    binding.overlayView.visibility = View.VISIBLE
                } else {
                    binding.recyclerViewProductos.visibility = View.GONE
                    binding.overlayView.visibility = View.GONE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        productosviewModel.productoModel.observe(this, Observer { productos ->
            if (productos != null) {
                busquedaProductosAdapter.updateData(productos)
            }
        })
    }


    private fun irBocadillos(categoria: String?, userID: Int) {
        binding.imageBocadillos.setOnClickListener {
            val intent = Intent(this, Bocadillos_Activity::class.java)
            intent.putExtra("USER_ID", userID)
            intent.putExtra("categoria", categoria)
            startActivity(intent)
        }
    }

    private fun irCesta() {
        binding.cesta.setOnClickListener{
            val userId = intent.getIntExtra("USER_ID", -1)
            val intent = Intent(this, Cesta_Activity::class.java)
            intent.putExtra("USER_ID", userId)
            startActivity(intent)
        }
    }


    private fun irBocadillosPersonalizados(userID: Int) {
        binding.imagenBocadillosPersonalizados.setOnClickListener {
            val intent = Intent(this, BocadillosPersonalizados_Activity::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

    private fun irPizzas(categoria: String?, userID: Int) {
        binding.imagenPizzas.setOnClickListener {
            val intent = Intent(this, Pizzas_Activity::class.java)
            intent.putExtra("categoria", categoria)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

    private fun irHamburguesas(categoria: String?, userID: Int) {
        binding.imagenHamburguesas.setOnClickListener {
            val intent = Intent(this, Hamburguesas_Activity::class.java)
            intent.putExtra("categoria", categoria)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

    private fun irBebidas(categoria: String?, userID: Int) {
        binding.imagenRefrescos.setOnClickListener {
            val intent = Intent(this, Bebidas_Activity::class.java)
            intent.putExtra("categoria", categoria)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.nav_valoraciones -> {
                val userId = intent.getIntExtra("USER_ID", -1)
                val intent = Intent(this, Valoraciones_Activity::class.java)
                intent.putExtra("USER_ID", userId)
                intent.putExtra("nombre", nombreUsuario)
                startActivity(intent)

            }

            R.id.nav_info -> {
                val intent = Intent(this, Informacion_Activity::class.java)
                startActivity(intent)
            }

            R.id.nav_HistorialPedidos -> {
                val userId = intent.getIntExtra("USER_ID", -1)
                val intent = Intent(this, Pedidos_Activity::class.java)
                intent.putExtra("USER_ID", userId)
                startActivity(intent)

            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    fun irInformacion() {
        binding.informacion.setOnClickListener {
            val intent = Intent(this, Informacion_Activity::class.java)
            startActivity(intent)
        }

    }

}