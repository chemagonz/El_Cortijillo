package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Pedidos_Repository
import com.advantys.el_cortijillo.Data.Repositories.Usuario_Repository
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Producto
import javax.inject.Inject

class Pedido_UseCase @Inject constructor(private val repository: Pedidos_Repository) {

    fun registroPedido(pedido: Pedido) {
        repository.registroPedido(pedido)
    }

    suspend  fun obtenerPedidos(usuario: Int?): List<Pedido?>{
        val pedido = repository.obtenerPedidos(usuario)

        return if(pedido.isNullOrEmpty())
            listOf<Pedido>()
        else pedido
    }
}