package com.advantys.el_cortijillo.UI.Views.Bebidas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Bebida
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Bebidas_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Bocadillos_ViewModel
import com.advantys.el_cortijillo.databinding.ItemBebidasBinding
import com.advantys.el_cortijillo.databinding.ItemBocadillosBinding
import com.bumptech.glide.Glide

class Bebidas_Adapter (private val bebidasList: List<Bebida?>, private val bebidaViewmodel: Bebidas_ViewModel): RecyclerView.Adapter<Bebidas_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Bebidas_ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Bebidas_ViewHolder(layoutInflater.inflate(R.layout.item_bebidas, parent, false))
    }

    override fun getItemCount(): Int = bebidasList.size
    override fun onBindViewHolder(holder: Bebidas_ViewHolder, position: Int) {
        holder.bind(bebidasList[position])

        val item = bebidasList[position]
        holder.binding.nombreBebida.text = item?.nombre
        holder.binding.descripcionBebida.text = item?.descripcion
        holder.binding.precioBebida.text = item?.precio.toString()
//        holder.binding.botonDetalles.setOnClickListener {
//            bocadilloViewmodel.b(item, holder.itemView.context)
//        }
    }
}

class Bebidas_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemBebidasBinding.bind(view)
    fun bind(bebidasModel: Bebida?){
        binding.nombreBebida.text = bebidasModel?.nombre
        binding.descripcionBebida.text = bebidasModel?.descripcion
        binding.precioBebida.text = bebidasModel?.precio.toString()
        Glide.with(itemView)
            .load(bebidasModel?.imagen)
            .into(binding.imagenBebida)
    }

}