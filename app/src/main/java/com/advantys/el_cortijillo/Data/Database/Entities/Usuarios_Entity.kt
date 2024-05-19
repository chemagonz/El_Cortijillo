package com.advantys.el_cortijillo.Data.Database.Entities

import android.content.ContentValues
import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.Usuario_Schema
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

data class Usuarios_Entity (
    var id: Int? = null,
    var nombre: String? = null,
    var direccion: String? = null,
    var telefono: String? = null,
    var correo: String? = null,
    var password: String? = null
) {

    companion object {
        fun fromCursor(cursor: Cursor): Usuarios_Entity {
            val modelo = Usuarios_Entity()
            modelo.id = cursor.getInt(Usuario_Schema.ID_FIELD, null)
            modelo.nombre = cursor.getString(Usuario_Schema.NOMBRE_FIELD, null)
            modelo.direccion = cursor.getString(Usuario_Schema.DIRECCION_FIELD, null)
            modelo.telefono = cursor.getString(Usuario_Schema.TELEFONO_FIELD, null)
            modelo.correo = cursor.getString(Usuario_Schema.CORREO_FIELD, null)
            modelo.password = cursor.getString(Usuario_Schema.CONTRASENA_FIELD, null)
            return modelo
        }
    }

    fun toContentValues(): ContentValues {
        val values= ContentValues()

        values.put(Usuario_Schema.NOMBRE_FIELD, nombre)
        values.put(Usuario_Schema.DIRECCION_FIELD, direccion)
        values.put(Usuario_Schema.TELEFONO_FIELD, telefono)
        values.put(Usuario_Schema.CORREO_FIELD, correo)
        values.put(Usuario_Schema.CONTRASENA_FIELD, password)

        return values
    }
}

fun Usuario.toEntity() = Usuarios_Entity(id, nombre, direccion, telefono, correo, password)
