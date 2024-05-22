package com.advantys.el_cortijillo.UI.Views.Bocadillos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.Pizza
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Bocadillos_ViewModel
import com.advantys.el_cortijillo.databinding.ItemBocadillosBinding
import com.bumptech.glide.Glide

class Bocadillos_Adapter  (private var bocadillosList: List<Bocadillo?>, private val bocadilloViewmodel: Bocadillos_ViewModel): RecyclerView.Adapter<Bocadillos_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Bocadillos_ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Bocadillos_ViewHolder(layoutInflater.inflate(R.layout.item_bocadillos, parent, false))
    }

    override fun getItemCount(): Int = bocadillosList.size
    override fun onBindViewHolder(holder: Bocadillos_ViewHolder, position: Int) {
        holder.bind(bocadillosList[position])

        val item = bocadillosList[position]
        holder.binding.nombreBocadillo.text = item?.nombre
        holder.binding.descripcionBocadillo.text = item?.descripcion
        holder.binding.precioBocadillo.text = item?.precio.toString()
//        holder.binding.botonDetalles.setOnClickListener {
//            bocadilloViewmodel.b(item, holder.itemView.context)
//        }
    }
}

class Bocadillos_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemBocadillosBinding.bind(view)
    fun bind(bocadillosModel: Bocadillo?){
        binding.nombreBocadillo.text = bocadillosModel?.nombre
        binding.descripcionBocadillo.text = bocadillosModel?.descripcion
        binding.precioBocadillo.text = bocadillosModel?.precio.toString()
        Glide.with(itemView)
            .load(bocadillosModel?.imagen)
            .into(binding.imagenBocadillo)
    }

}