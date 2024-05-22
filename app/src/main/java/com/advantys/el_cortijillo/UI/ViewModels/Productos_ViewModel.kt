package com.advantys.el_cortijillo.UI.ViewModels

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Cesta
import com.advantys.el_cortijillo.Domain.Models.Pizza
import com.advantys.el_cortijillo.Domain.Models.Producto
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Domain.UseCases.Cesta_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Pizzas_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Producto_UseCase
import com.advantys.el_cortijillo.UI.Views.Productos.ProductoDetalles_Activity
import com.advantys.el_cortijillo.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Productos_ViewModel @Inject constructor(private val productoUsecase: Producto_UseCase,  private val cestaUsecase: Cesta_UseCase): ViewModel() {

    val productoModel = MutableLiveData<List<Producto?>>()
    val productoModelDet = MutableLiveData<Producto?>()

    private var _mensajeRegistro = MutableLiveData<Respuesta>()
    val mensajeRegistro : LiveData<Respuesta> get() = _mensajeRegistro


    @SuppressLint("SuspiciousIndentation")
    fun obtenerProducto(filtro: String) {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = productoUsecase.obtenerProductos(filtro)
            if(!resultado.isNullOrEmpty()) productoModel.postValue(resultado)
        }
    }

    fun obtenerProductosCategoria(categoria: String?) {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = productoUsecase.obtenerProductosCategoria(categoria)
            if(!resultado.isNullOrEmpty()) productoModel.postValue(resultado)
        }
    }

    fun obtenerProductoDetalle(categoria: String?, id: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = productoUsecase.obtenerProductosDetalles(categoria,id)
            if(resultado != null) productoModelDet.postValue(resultado)
        }
    }

    fun insertCestaItems(
        usuarioID: Int?, productoID: Int? = null, bocadilloPersonalizadoID: Int? = null, cantidad: Int, nombreProducto: String? =  null, descripcion: String? = null,
        precioProducto: Double? = null, imagenProducto: String? = null, nombreBocadillo:String? = null, ingredientesBocadillo: String? = null, precioBocadillo: Double? = null, imagenBocadillo:String? = null) {
        viewModelScope.launch {
            try {
                cestaUsecase.insertCestaItems(usuarioID, productoID, bocadilloPersonalizadoID, cantidad, nombreProducto, descripcion, precioProducto, imagenProducto, nombreBocadillo, ingredientesBocadillo, precioBocadillo, imagenBocadillo)
                _mensajeRegistro.postValue(Respuesta.ok("Añadido con éxito a la cests"))
            } catch (e: Exception) {
                _mensajeRegistro.postValue(Respuesta.error("Error al añadir a la cesta"))
            }
        }
    }

    fun btnDetalle(item: Producto?, userID: Int, context: Context) {
        val intent = Intent(context,ProductoDetalles_Activity::class.java)
        intent.putExtra("USER_ID", userID)
        intent.putExtra("productoID", item?.productoID)
        intent.putExtra("nombre", item?.nombre)
        intent.putExtra("precio", item?.precio)
        intent.putExtra("imagen", item?.imagen)
        intent.putExtra("descripcion", item?.descripcion)
        intent.putExtra("categoria", item?.categoria)
        intent.putExtra("id", item?.itemID)
        context.startActivity(intent)
    }
}