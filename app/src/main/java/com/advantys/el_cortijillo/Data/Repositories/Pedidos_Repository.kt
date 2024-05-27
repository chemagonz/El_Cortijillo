package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.Cesta_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.Pedidos_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Pedidos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Productos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.toEntity
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Producto
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Domain.Models.toDomain

import javax.inject.Inject

class Pedidos_Repository @Inject constructor(private val pedidoDao: Pedidos_Dao)  {

    fun registroPedido (pedido: Pedido) {
        pedidoDao.registroPedido(pedido.toEntity())
    }

    suspend fun obtenerPedidos(usuario: Int?): List<Pedido?> {
        val response: List<Pedidos_Entity?> = pedidoDao.obtenerPedidos(usuario)
        return response.filterNotNull().map { it.toDomain() }
    }
}