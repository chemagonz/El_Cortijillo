package com.advantys.el_cortijillo.Data.Database.Entities

import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.Bebidas_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Bocadillos_Schema
import com.advantys.el_cortijillo.Utils.getDouble
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

class Bebidas_Entity (

    var id: Int? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var precio: Double? = null,
    var imagen: String? = null

)
{
    companion object {
        fun fromCursor(cursor: Cursor): Bebidas_Entity {
            val modelo = Bebidas_Entity()
            modelo.id = cursor.getInt(Bebidas_Schema.ID_FIELD, null)
            modelo.nombre = cursor.getString(Bebidas_Schema.NOMBRE_FIELD, null)
            modelo.descripcion = cursor.getString(Bebidas_Schema.DESCRIPCION_FIELD, null)
            modelo.precio = cursor.getDouble(Bebidas_Schema.PRECIO_FIELD, null)
            modelo.imagen = cursor.getString(Bebidas_Schema.IMAGEN_FIELD, null)
            return modelo
        }
    }
}