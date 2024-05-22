package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.BD
import com.advantys.el_cortijillo.Data.Database.Entities.Usuarios_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Usuario_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class RegistoUsuario_Dao @Inject constructor(private val databaseManager: BDUtils) {


    fun registroUsuario(usuario: Usuarios_Entity) {
        databaseManager.insert(Usuario_Schema.TABLE_NAME, usuario.toContentValues())
    }

    fun obtenerUsuario(userID: Int): String? {
        val sql = "SELECT ${Usuario_Schema.TABLE_NAME}.${Usuario_Schema.NOMBRE_FIELD} FROM ${Usuario_Schema.TABLE_NAME} WHERE ${Usuario_Schema.TABLE_NAME}.${Usuario_Schema.ID_FIELD} = $userID "
        return databaseManager.getSelectScalarString(sql)

    }

    fun obtenerUsuarioDetalles(usuario: Int?): Usuarios_Entity? {
        val sql = "SELECT * FROM ${Usuario_Schema.TABLE_NAME} WHERE ${Usuario_Schema.ID_FIELD} = $usuario "
        return databaseManager.queryDetalles(sql) { cursor ->
            Usuarios_Entity.fromCursor(cursor)
        }
    }

    fun verificarCredenciales(usuario: String, password: String): Int? {
         return databaseManager.verificarCredenciales(Usuario_Schema.TABLE_NAME, Usuario_Schema.ID_FIELD, Usuario_Schema.NOMBRE_FIELD, Usuario_Schema.CONTRASENA_FIELD, usuario, password )
    }

}