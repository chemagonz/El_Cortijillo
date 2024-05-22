package com.advantys.el_cortijillo.Data.Database.Entities

import android.content.ContentValues
import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.BocadillosPersonalizados_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Bocadillos_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Usuario_Schema
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Utils.getDouble
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

class BocadillosPersonalizados_Entity  (

    var id: Int? = null,
    var nombre: String? = null,
    var ingrediente1: String? = null,
    var ingrediente2: String? = null,
    var ingrediente3: String? = null,
    var ingrediente4: String? = null,
    var ingrediente5: String? = null,
    var precio: Double? = null,
    var usuarioID: Int? = null
)

{
    companion object {
        fun fromCursor(cursor: Cursor): BocadillosPersonalizados_Entity {
            val modelo = BocadillosPersonalizados_Entity()
            modelo.id = cursor.getInt(BocadillosPersonalizados_Schema.ID_FIELD, null)
            modelo.nombre = cursor.getString(BocadillosPersonalizados_Schema.NOMBRE_FIELD, null)
            modelo.ingrediente1 = cursor.getString(BocadillosPersonalizados_Schema.INGREDIENTE1_FIELD, null)
            modelo.ingrediente2 = cursor.getString(BocadillosPersonalizados_Schema.INGREDIENTE2_FIELD, null)
            modelo.ingrediente3 = cursor.getString(BocadillosPersonalizados_Schema.INGREDIENTE3_FIELD, null)
            modelo.ingrediente4 = cursor.getString(BocadillosPersonalizados_Schema.INGREDIENTE4_FIELD, null)
            modelo.ingrediente5 = cursor.getString(BocadillosPersonalizados_Schema.INGREDIENTE5_FIELD, null)
            modelo.precio = cursor.getDouble(BocadillosPersonalizados_Schema.PRECIO_FIELD, null)
            modelo.usuarioID = cursor.getInt(BocadillosPersonalizados_Schema.USUARIO_FIELD,null)
            return modelo
        }
    }

    fun toContentValues(): ContentValues {
        val values= ContentValues()

        values.put(BocadillosPersonalizados_Schema.NOMBRE_FIELD, nombre)
        values.put(BocadillosPersonalizados_Schema.INGREDIENTE1_FIELD, ingrediente1)
        values.put(BocadillosPersonalizados_Schema.INGREDIENTE2_FIELD, ingrediente2)
        values.put(BocadillosPersonalizados_Schema.INGREDIENTE3_FIELD, ingrediente3)
        values.put(BocadillosPersonalizados_Schema.INGREDIENTE4_FIELD, ingrediente4)
        values.put(BocadillosPersonalizados_Schema.INGREDIENTE5_FIELD, ingrediente5)
        values.put(BocadillosPersonalizados_Schema.PRECIO_FIELD, precio)
        values.put(BocadillosPersonalizados_Schema.USUARIO_FIELD, usuarioID)

        return values
    }


}

fun BocadilloPersonalizado.toEntity() = BocadillosPersonalizados_Entity(id, nombre, ingrediente1, ingrediente2, ingrediente3, ingrediente4, ingrediente5, precio, usuarioID)



