package com.advantys.el_cortijillo.UI.Views.Pizzas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Hamburguesa
import com.advantys.el_cortijillo.Domain.Models.Pizza
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Hamburguesas_ViewModel
import com.advantys.el_cortijillo.UI.ViewModels.Pizzas_ViewModel
import com.advantys.el_cortijillo.databinding.ItemHamburguesasBinding
import com.advantys.el_cortijillo.databinding.ItemPizzasBinding
import com.bumptech.glide.Glide

class Pizzas_Adapter (private var pizzasList: List<Pizza?>, private val pizzaViewmodel: Pizzas_ViewModel): RecyclerView.Adapter<Pizzas_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pizzas_ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Pizzas_ViewHolder(layoutInflater.inflate(R.layout.item_pizzas, parent, false))
    }

    override fun getItemCount(): Int = pizzasList.size
    override fun onBindViewHolder(holder: Pizzas_ViewHolder, position: Int) {
        holder.bind(pizzasList[position])

        val item = pizzasList[position]
        holder.binding.nombrePizzas.text = item?.nombre
        holder.binding.descripcionPizzas.text = item?.descripcion
        holder.binding.precioPizzas.text = item?.precio.toString()
//        holder.binding.botonDetalles.setOnClickListener {
//            bocadilloViewmodel.b(item, holder.itemView.context)
//        }
    }
}

class Pizzas_ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val binding = ItemPizzasBinding.bind(view)
    fun bind(pizzaModel: Pizza?){

        binding.nombrePizzas.text = pizzaModel?.nombre
        binding.descripcionPizzas.text = pizzaModel?.descripcion
        binding.precioPizzas.text = pizzaModel?.precio.toString()
        Glide.with(itemView)
            .load(pizzaModel?.imagen)
            .into(binding.imagenPizzas)
    }

}