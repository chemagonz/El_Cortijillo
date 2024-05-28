package com.advantys.el_cortijillo.Data.Database.Entities

import android.content.ContentValues
import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.Pedidos_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Valoraciones_Schema
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Valoracion
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

class Valoraciones_Entity (

    var id: Int? = null,
    var usuarioID: Int? = null,
    var valoracion: String? = null,
    var fecha: String? = null,
    var nombreUsuario: String? = null


)
{
    companion object {
        fun fromCursor(cursor: Cursor): Valoraciones_Entity {
            val modelo = Valoraciones_Entity()
            modelo.id = cursor.getInt(Valoraciones_Schema.ID_FIELD, null)
            modelo.usuarioID = cursor.getInt(Valoraciones_Schema.ID_USUARIO_FIELD, null)
            modelo.valoracion = cursor.getString(Valoraciones_Schema.VALORACION_FIELD, null)
            modelo.fecha = cursor.getString(Valoraciones_Schema.FECHA_FIELD, null)
            modelo.nombreUsuario = cursor.getString(Valoraciones_Schema.NOMBRE_FIELD, null)
            return modelo
        }
    }

    fun toContentValues(): ContentValues {
        val values= ContentValues()

        values.put(Valoraciones_Schema.ID_USUARIO_FIELD, usuarioID)
        values.put(Valoraciones_Schema.FECHA_FIELD, fecha)
        values.put(Valoraciones_Schema.VALORACION_FIELD, valoracion)
        values.put(Valoraciones_Schema.NOMBRE_FIELD, nombreUsuario)
        return values
    }

}
fun Valoracion.toEntity() = Valoraciones_Entity(id, usuarioID, valoracion, fecha, nombreUsuario)