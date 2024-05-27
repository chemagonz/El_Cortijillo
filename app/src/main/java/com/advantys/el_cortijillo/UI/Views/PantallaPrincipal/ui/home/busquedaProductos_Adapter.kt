package com.advantys.el_cortijillo.UI.Views.PantallaPrincipal.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.el_cortijillo.Domain.Models.Producto
import com.advantys.el_cortijillo.R
import com.advantys.el_cortijillo.UI.ViewModels.Productos_ViewModel
import com.advantys.el_cortijillo.Utils.mostrarSnackbar
import com.advantys.el_cortijillo.databinding.ItemPizzasBinding
import com.advantys.el_cortijillo.databinding.ItemProductosBinding
import com.bumptech.glide.Glide

class busquedaProductos_Adapter (private var productosList: List<Producto?>, private val productoViewmodel: Productos_ViewModel,  private val userId: Int): RecyclerView.Adapter<Producto_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Producto_ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Producto_ViewHolder(layoutInflater.inflate(R.layout.item_productos, parent, false))
    }

    override fun getItemCount(): Int = productosList.size
    override fun onBindViewHolder(holder: Producto_ViewHolder, position: Int) {
        holder.bind(productosList[position])

        val item = productosList[position]
        holder.binding.nombreProducto.text = item?.nombre
        holder.binding.descripcionProducto.text = item?.descripcion
        holder.binding.precioProducto.text = item?.precio.toString()
        holder.binding.botonDetalles.setOnClickListener {
            productoViewmodel.btnDetalle(item, userId, holder.itemView.context)
        }

        holder.binding.botonAddCarrito.setOnClickListener {
            productoViewmodel.insertCestaItems(userId, item?.productoID, null, 1, item?.nombre, item?.descripcion, item?.precio, item?.imagen)

        }

    }

    fun updateData(newSearchResults: List<Producto?>) {
        this.productosList = newSearchResults
        notifyDataSetChanged()
    }
}

class Producto_ViewHolder(view: View): RecyclerView.ViewHolder(view){

    val binding = ItemProductosBinding.bind(view)
    fun bind(productosModel: Producto?){

        binding.nombreProducto.text = productosModel?.nombre
        binding.descripcionProducto.text = productosModel?.descripcion
        binding.precioProducto.text = productosModel?.precio.toString()
        Glide.with(itemView)
            .load(productosModel?.imagen)
            .into(binding.imagenProducto)
    }

}