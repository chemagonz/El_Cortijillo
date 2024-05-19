package com.advantys.el_cortijillo.Data.Database.Entities

import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.Hamburguesas_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Pizzas_Schema
import com.advantys.el_cortijillo.Utils.getDouble
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

class Pizzas_Entity (

    var id: Int? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var precio: Double? = null,
    var imagen: String? = null

)
{
    companion object {
        fun fromCursor(cursor: Cursor): Pizzas_Entity {
            val modelo = Pizzas_Entity()
            modelo.id = cursor.getInt(Pizzas_Schema.ID_FIELD, null)
            modelo.nombre = cursor.getString(Pizzas_Schema.NOMBRE_FIELD, null)
            modelo.descripcion = cursor.getString(Pizzas_Schema.DESCRIPCION_FIELD, null)
            modelo.precio = cursor.getDouble(Pizzas_Schema.PRECIO_FIELD, null)
            modelo.imagen = cursor.getString(Pizzas_Schema.IMAGEN_FIELD, null)
            return modelo
        }
    }
}