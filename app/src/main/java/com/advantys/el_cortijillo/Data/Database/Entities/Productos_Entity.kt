package com.advantys.el_cortijillo.Data.Database.Entities

import com.advantys.el_cortijillo.Data.Database.Schemas.Productos_Schema
import com.google.firestore.v1.Cursor
import com.advantys.el_cortijillo.Utils.getDouble
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

class Productos_Entity (

    var productoID: Int? = null,
    var categoria: String? = null,
    var itemID: Int? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var precio: Double? = null,
    var imagen: String? = null
) {
    companion object {
        fun fromCursor(cursor: android.database.Cursor): Productos_Entity {
            val modelo = Productos_Entity()
            modelo.productoID = cursor.getInt(Productos_Schema.PRODUCTO_ID_FIELD, null)
            modelo.categoria = cursor.getString(Productos_Schema.CATEGORIA_FIELD,null)
            modelo.itemID = cursor.getInt(Productos_Schema.ITEM_ID_FIELD,null)
            modelo.nombre = cursor.getString(Productos_Schema.NOMBRE_FIELD,null)
            modelo.descripcion = cursor.getString(Productos_Schema.DESCRIPCION_FIELD,null)
            modelo.precio = cursor.getDouble(Productos_Schema.PRECIO_FIELD,null)
            modelo.imagen = cursor.getString(Productos_Schema.IMAGEN_FIELD,null)
            return modelo
        }
    }
}