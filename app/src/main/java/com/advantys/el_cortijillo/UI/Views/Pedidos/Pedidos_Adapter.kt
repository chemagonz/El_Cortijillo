package com.advantys.el_cortijillo.UI.Views.Pedidos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Pedidos_ViewModel
import com.advantys.el_cortijillo.databinding.ItemPedidosBinding
import com.bumptech.glide.Glide

class Pedidos_Adapter(private var pedidosList: List<Pedido?>, private val pedidosViewmodel: Pedidos_ViewModel, private val userId: Int): RecyclerView.Adapter<Pedido_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pedido_ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Pedido_ViewHolder(layoutInflater.inflate(R.layout.item_pedidos, parent, false))
    }

    override fun getItemCount(): Int = pedidosList.size
    override fun onBindViewHolder(holder: Pedido_ViewHolder, position: Int) {
        holder.bind(pedidosList[position])

        val item = pedidosList[position]
        holder.binding.pedidoID.text = item?.id.toString()
        holder.binding.total.text = item?.total
        holder.binding.fechaDia.text = item?.fecha.toString()
        holder.binding.fecha.text = item?.horaRecogida.toString()

    }


}

class Pedido_ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val binding = ItemPedidosBinding.bind(view)
    fun bind(pedidoModel: Pedido?){

        binding.pedidoID.text = pedidoModel?.id.toString()
        binding.total.text = pedidoModel?.total
        binding.fechaDia.text = pedidoModel?.fecha.toString()
        binding.fecha.text = pedidoModel?.horaRecogida.toString()

        Glide.with(itemView)
            .load("https://cdn-icons-png.flaticon.com/512/2558/2558162.png")
            .into(binding.imagen)
    }

}