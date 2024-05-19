package com.advantys.el_cortijillo.UI.Views.Hamburguesas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Bebida
import com.advantys.el_cortijillo.Domain.Models.Hamburguesa
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Bebidas_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Hamburguesas_ViewModel
import com.advantys.el_cortijillo.databinding.ItemBebidasBinding
import com.advantys.el_cortijillo.databinding.ItemHamburguesasBinding
import com.bumptech.glide.Glide

class Hamburguesas_Adapter (private val hamburguesasList: List<Hamburguesa?>, private val hamburguesasViewmodel: Hamburguesas_ViewModel): RecyclerView.Adapter<Hamburguesas_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Hamburguesas_ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Hamburguesas_ViewHolder(layoutInflater.inflate(R.layout.item_hamburguesas, parent, false))
    }

    override fun getItemCount(): Int = hamburguesasList.size
    override fun onBindViewHolder(holder: Hamburguesas_ViewHolder, position: Int) {
        holder.bind(hamburguesasList[position])

        val item = hamburguesasList[position]
        holder.binding.nombreHamburguesa.text = item?.nombre
        holder.binding.descripcionHamburguesa.text = item?.descripcion
        holder.binding.precioHamburguesa.text = item?.precio.toString()
//        holder.binding.botonDetalles.setOnClickListener {
//            bocadilloViewmodel.b(item, holder.itemView.context)
//        }
    }
}

class Hamburguesas_ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val binding = ItemHamburguesasBinding.bind(view)
    fun bind(hamburguesaModel: Hamburguesa?){

        binding.nombreHamburguesa.text = hamburguesaModel?.nombre
        binding.descripcionHamburguesa.text = hamburguesaModel?.descripcion
        binding.precioHamburguesa.text = hamburguesaModel?.precio.toString()
        Glide.with(itemView)
            .load(hamburguesaModel?.imagen)
            .into(binding.imagenHamburguesa)
    }

}