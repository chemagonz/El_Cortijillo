package com.advantys.el_cortijillo.Data.Database.Entities

import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.Hamburguesas_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Ingredientes_Schema
import com.advantys.el_cortijillo.Utils.getDouble
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

class Ingredientes_Entity(

    var id: Int? = null,
    var nombre: String? = null,
)
{

    companion object {
        fun fromCursor(cursor: Cursor): Ingredientes_Entity {
            val modelo = Ingredientes_Entity()
            modelo.id = cursor.getInt(Ingredientes_Schema.ID_FIELD, null)
            modelo.nombre = cursor.getString(Ingredientes_Schema.NOMBRE_FIELD, null)
            return modelo
        }
    }
}