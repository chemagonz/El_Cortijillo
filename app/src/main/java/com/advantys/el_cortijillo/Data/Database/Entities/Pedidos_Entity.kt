package com.advantys.el_cortijillo.Data.Database.Entities

import android.content.ContentValues
import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.Bebidas_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Pedidos_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Usuario_Schema
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Utils.getDouble
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getLocalDateTime
import com.advantys.el_cortijillo.Utils.getString
import com.google.type.DateTime
import java.time.LocalDateTime
import java.util.Locale

class Pedidos_Entity(

    var id: Int? = null,
    var usuarioID: Int? = null,
    var total: String? = null,
    var fecha: String? = null,
    var horaRecogida: String? = null

)
{
    companion object {
        fun fromCursor(cursor: Cursor): Pedidos_Entity {
            val modelo = Pedidos_Entity()
            modelo.id = cursor.getInt(Pedidos_Schema.ID_FIELD, null)
            modelo.usuarioID = cursor.getInt(Pedidos_Schema.ID_USUARIO_FIELD, null)
            modelo.total = cursor.getString(Pedidos_Schema.TOTAL_FIELD, null)
            modelo.fecha = cursor.getString(Pedidos_Schema.FECHAHORA_FIELD, null)
            modelo.horaRecogida = cursor.getString(Pedidos_Schema.HORARECOGIDA_FIELD, null)
            return modelo
        }
    }

    fun toContentValues(): ContentValues {
        val values= ContentValues()

        values.put(Pedidos_Schema.ID_USUARIO_FIELD, usuarioID)
        values.put(Pedidos_Schema.FECHAHORA_FIELD, fecha)
        values.put(Pedidos_Schema.TOTAL_FIELD, total)
        values.put(Pedidos_Schema.HORARECOGIDA_FIELD, horaRecogida)
        return values
    }

}
fun Pedido.toEntity() = Pedidos_Entity(id, usuarioID, total, fecha, horaRecogida)