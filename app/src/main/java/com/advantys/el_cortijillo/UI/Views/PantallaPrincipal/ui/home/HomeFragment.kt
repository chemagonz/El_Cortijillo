package com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.advantys.el_cortijillo.UI.Views.Bebidas.Bebidas_Activity
import com.advantys.el_cortijillo.UI.Views.Bocadillos.Bocadillos_Activity
import com.advantys.el_cortijillo.UI.Views.Hamburguesas.Hamburguesas_Activity
import com.advantys.el_cortijillo.UI.Views.Pizzas.Pizzas_Activity
import com.advantys.el_cortijillo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

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

        irBocadillos()
        irPizzas()
        irHamburguesas()
        irBebidas()

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun irBocadillos() {
        binding.imageBocadillos.setOnClickListener {
            val intent = Intent(requireContext(), Bocadillos_Activity::class.java)
            startActivity(intent)
        }
    }

    private fun irPizzas() {
        binding.imagenPizzas.setOnClickListener {
            val intent = Intent(requireContext(), Pizzas_Activity::class.java)
            startActivity(intent)
        }
    }

    private fun irHamburguesas() {
        binding.imagenHamburguesas.setOnClickListener {
            val intent = Intent(requireContext(), Hamburguesas_Activity::class.java)
            startActivity(intent)
        }
    }

    private fun irBebidas() {
        binding.imagenRefrescos.setOnClickListener {
            val intent = Intent(requireContext(), Bebidas_Activity::class.java)
            startActivity(intent)
        }
    }

}