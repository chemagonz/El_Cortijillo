package com.advantys.el_cortijillo.UI.Views.Cesta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Cesta
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Cesta_ViewModel
import com.advantys.el_cortijillo.databinding.ItemCestaBinding
import com.bumptech.glide.Glide

class Cesta_Adapter(private var cestaList: MutableList<Cesta?>, private val cestaViewmodel: Cesta_ViewModel) : RecyclerView.Adapter<Cesta_ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Cesta_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Cesta_ViewHolder(layoutInflater.inflate(R.layout.item_cesta, parent, false))
    }

    override fun getItemCount(): Int = cestaList.size

    override fun onBindViewHolder(holder: Cesta_ViewHolder, position: Int) {
        val item = cestaList[position]
        holder.bind(item)
        holder.binding.botonBorrarCarrito.setOnClickListener {
            if (cestaList.isNotEmpty()) {
                item?.let {
                    removeItem(item)
                    cestaViewmodel.deleteCestaItem(it.id)
                    calcularTotal()

                }
            }
        }
    }
    fun calcularTotal(): String {
        var total = 0.0
        cestaList.forEach { item ->
            item?.let {
                total += if (it.productoID != null) {
                    it.precioProducto!! * it.cantidad!!
                } else {
                    it.precioBocadillo!! * it.cantidad!!
                }
            }
        }
        return String.format("%.2f", total)
    }

    fun updateData(newList: List<Cesta?>) {
        cestaList = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(item: Cesta?) {
        val position = cestaList.indexOf(item)
        if (position != -1) {
            cestaList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

}

class Cesta_ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemCestaBinding.bind(view)


    fun bind(cestaModel: Cesta?) {
        cestaModel?.let { item ->
            if (item.productoID != null) {
                // Es un producto
                binding.nombre.text = item.nombreProducto
                binding.descripcion.visibility = View.VISIBLE
                binding.descripcion.text = item.descripcionProducto
                binding.precio.text = item.precioProducto.toString()
                binding.cantidad.text = item.cantidad.toString()
                Glide.with(itemView)
                    .load(item.imagenProducto)
                    .into(binding.imagen)
            } else {
                // Es un bocadillo personalizado
                binding.nombre.text = item.nombreBocadillo
                binding.descripcion.visibility = View.GONE
                binding.precio.text = item.precioBocadillo.toString()
                binding.cantidad.text = item.cantidad.toString()
                Glide.with(itemView)
                    .load(R.mipmap.bocadillos_personalizados_foreground)
                    .into(binding.imagen)
            }
        }
    }
}