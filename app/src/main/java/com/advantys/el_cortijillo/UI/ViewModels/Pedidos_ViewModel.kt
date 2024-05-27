package com.advantys.el_cortijillo.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Producto
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Domain.UseCases.Pedido_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Usuario_UseCase
import com.advantys.el_cortijillo.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Pedidos_ViewModel  @Inject constructor(private val pedidoUsecase: Pedido_UseCase): ViewModel() {

    val pedidoModel = MutableLiveData<List<Pedido?>>()


    private var _mensajeRegistro = MutableLiveData<Respuesta>()
    val mensajeRegistro : LiveData<Respuesta> get() = _mensajeRegistro
    fun registroPedido(pedido: Pedido) {
        viewModelScope.launch {
            try {
                pedidoUsecase.registroPedido(pedido)
                _mensajeRegistro.postValue(Respuesta.ok("Pedido realizado con éxito"))
            } catch (e: Exception) {
                _mensajeRegistro.postValue(Respuesta.error("Error al realizar pedido: ${e.message}"))
            }
        }
    }

    fun obtenerPedidos(usuario: Int?) {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = pedidoUsecase.obtenerPedidos(usuario)
            if(!resultado.isNullOrEmpty()) pedidoModel.postValue(resultado)
        }
    }

}