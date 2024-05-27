package com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.advantys.el_cortijillo.UI.ViewModels.Pedidos_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Usuario_ViewModel
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.ui.home.HomeFragment
import com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.ui.home.busquedaProductos_Adapter
import com.advantys.el_cortijillo.databinding.FragmentSlideshowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private lateinit var pedidosAdapter: Pedidos_Adapter
    private var _binding: FragmentSlideshowBinding? = null
    val pedidoViewmodel: Pedidos_ViewModel by viewModels()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root




        mostrarPedidos()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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

    private fun mostrarPedidos() {
        val userId = arguments?.getInt("USER_ID") ?: -1



        pedidosAdapter = Pedidos_Adapter(emptyList(), pedidoViewmodel, userId)
        binding.recyclerviewPedidos.adapter = pedidosAdapter
        binding.recyclerviewPedidos.layoutManager = LinearLayoutManager(context)
    }
}