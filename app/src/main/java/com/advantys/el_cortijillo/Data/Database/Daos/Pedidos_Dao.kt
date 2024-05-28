package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.Pedidos_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Pedidos_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Pedidos_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun registroPedido(pedido: Pedidos_Entity) {
        databaseManager.insert(Pedidos_Schema.TABLE_NAME, pedido.toContentValues())
    }

    fun obtenerPedidos(usuario: Int?): List<Pedidos_Entity> {
        val sql = "SELECT * FROM ${Pedidos_Schema.TABLE_NAME} WHERE ${Pedidos_Schema.ID_USUARIO_FIELD} = $usuario"
        return  databaseManager.query(sql) { cursor ->
            Pedidos_Entity.fromCursor(cursor)
        }
    }
}