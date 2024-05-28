package com.advantys.el_cortijillo.UI.Views.Valoraciones

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Valoracion
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Pedidos_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Valoraciones_ViewModel
import com.advantys.el_cortijillo.databinding.ItemPedidosBinding
import com.advantys.el_cortijillo.databinding.ItemValoracionesBinding
import com.bumptech.glide.Glide

class Valoraciones_Adapter(private var valoracionesList: List<Valoracion?>, private val valoracionesViewmodel: Valoraciones_ViewModel): RecyclerView.Adapter<Valoracion_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Valoracion_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Valoracion_ViewHolder(layoutInflater.inflate(R.layout.item_valoraciones, parent, false))
    }

    override fun getItemCount(): Int = valoracionesList.size

    override fun onBindViewHolder(holder: Valoracion_ViewHolder, position: Int) {
        holder.bind(valoracionesList[position])
    }

    fun updateData(newValoracionesList: List<Valoracion?>) {
        valoracionesList = newValoracionesList
        notifyDataSetChanged()
    }
}

class Valoracion_ViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val binding = ItemValoracionesBinding.bind(view)

    fun bind(valoracionModel: Valoracion?) {
        binding.valoracion.text = valoracionModel?.valoracion
        binding.fechaValoracion.text = valoracionModel?.fecha
        binding.nombreUsuario.text = valoracionModel?.nombreUsuario

        Glide.with(itemView)
            .load("https://cdn-icons-png.flaticon.com/512/5987/5987420.png")
            .into(binding.imagen)
    }
}