package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.Productos_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Productos_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Productos_Dao @Inject constructor(private val databaseManager: BDUtils)  {

    fun obtenerProductos(filtro: String): List<Productos_Entity?> {

        val sql =  "SELECT * FROM ${Productos_Schema.TABLE_NAME} WHERE ${Productos_Schema.NOMBRE_FIELD} LIKE '%$filtro%'"
        return databaseManager.query(sql) { cursor ->
            Productos_Entity.fromCursor(cursor)
        }
    }

    fun obtenerProductoCategoria(categoria: String?): List<Productos_Entity?> {
        val sql =  "SELECT * FROM ${Productos_Schema.TABLE_NAME} WHERE ${Productos_Schema.CATEGORIA_FIELD} = '${categoria}' "
        return databaseManager.query(sql) { cursor ->
            Productos_Entity.fromCursor(cursor)
        }
    }

    fun obtenerProductosDetalle(categoria: String?, id: Int): Productos_Entity?{
        val sql =  "SELECT * FROM ${Productos_Schema.TABLE_NAME} WHERE ${Productos_Schema.CATEGORIA_FIELD} = '${categoria}' AND ${Productos_Schema.ITEM_ID_FIELD} = $id "
        return databaseManager.queryDetalles(sql) { cursor ->
            Productos_Entity.fromCursor(cursor)
        }
    }
}