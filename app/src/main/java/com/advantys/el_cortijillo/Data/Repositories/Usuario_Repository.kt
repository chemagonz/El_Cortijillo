package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.RegistoUsuario_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Usuarios_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.toEntity
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class Usuario_Repository @Inject constructor(private val usuariosDao: RegistoUsuario_Dao) {

    fun registroUsuario (usuario: Usuario) {
        usuariosDao.registroUsuario(usuario.toEntity())
    }

    fun obtenerUsuario (usuario: String, password: String): Usuario? {
        val response : Usuarios_Entity? = usuariosDao.obtenerUsuario(usuario, password)
        return response?.toDomain()
    }
}