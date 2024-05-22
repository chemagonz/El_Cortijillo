package com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.ui.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Usuario_ViewModel
import com.advantys.el_cortijillo.UI.Views.Bebidas.Bebidas_Activity
import com.advantys.el_cortijillo.UI.Views.Bocadillos.Bocadillos_Activity
import com.advantys.el_cortijillo.UI.Views.Hamburguesas.Hamburguesas_Activity
import com.advantys.el_cortijillo.UI.Views.BocadillosPersonalizados.BocadillosPersonalizados_Activity
import com.advantys.el_cortijillo.UI.Views.Pizzas.Pizzas_Activity
import com.advantys.el_cortijillo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var busquedaProductosAdapter: busquedaProductos_Adapter

    val productosviewModel: Productos_ViewModel by viewModels()
    val usuarioViewmodel: Usuario_ViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root




        busquedaProductos()

        val userId = arguments?.getInt("USER_ID") ?: -1
        if (userId != -1) {
            usuarioViewmodel.obtenerUsuarioDetalles(userId)
        }

        busquedaProductosAdapter = busquedaProductos_Adapter(emptyList(), productosviewModel, userId)
        binding.recyclerViewProductos.adapter = busquedaProductosAdapter
        binding.recyclerViewProductos.layoutManager = LinearLayoutManager(context)

        irBocadillos("Bocadillo",userId)
        irPizzas("Pizza",userId)
        irHamburguesas("Hamburguesa",userId)
        irBebidas("Bebida",userId)
        irBocadillosPersonalizados(userId)

        // Observa los cambios en el nombre del usuario
        usuarioViewmodel.usuarioModel.observe(viewLifecycleOwner, Observer { nombre ->
            nombre?.let {
                binding.welcome.text = "Bienvenido ${nombre.nombre} !"
            }
        })

        return root

    }

    companion object {
        fun newInstance(userId: Int): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putInt("USER_ID", userId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

        productosviewModel.productoModel.observe(viewLifecycleOwner, Observer { productos ->
            if (productos != null) {
                busquedaProductosAdapter.updateData(productos)
            }
        })
    }


    private fun irBocadillos(categoria: String?, userID: Int) {
        binding.imageBocadillos.setOnClickListener {
            val intent = Intent(requireContext(), Bocadillos_Activity::class.java)
            intent.putExtra("USER_ID", userID)
            intent.putExtra("categoria", categoria)
            startActivity(intent)
        }
    }


    private fun irBocadillosPersonalizados(userID: Int) {
        binding.imagenBocadillosPersonalizados.setOnClickListener {
            val intent = Intent(requireContext(), BocadillosPersonalizados_Activity::class.java)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

    private fun irPizzas(categoria: String?, userID: Int) {
        binding.imagenPizzas.setOnClickListener {
            val intent = Intent(requireContext(), Pizzas_Activity::class.java)
            intent.putExtra("categoria", categoria)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

    private fun irHamburguesas(categoria: String?, userID: Int) {
        binding.imagenHamburguesas.setOnClickListener {
            val intent = Intent(requireContext(), Hamburguesas_Activity::class.java)
            intent.putExtra("categoria", categoria)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

    private fun irBebidas(categoria: String?, userID: Int) {
        binding.imagenRefrescos.setOnClickListener {
            val intent = Intent(requireContext(), Bebidas_Activity::class.java)
            intent.putExtra("categoria", categoria)
            intent.putExtra("USER_ID", userID)
            startActivity(intent)
        }
    }

}