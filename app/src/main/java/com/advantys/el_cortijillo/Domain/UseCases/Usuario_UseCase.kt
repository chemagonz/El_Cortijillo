package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Usuario_Repository
import com.advantys.el_cortijillo.Domain.Models.Usuario
import javax.inject.Inject

class Usuario_UseCase @Inject constructor(private val repository: Usuario_Repository) {

    suspend fun registroUsuario(usuario: Usuario) {
        repository.registroUsuario(usuario)
    }

    suspend fun obtenerUsuario(userID: Int): String? {
      return  repository.obtenerUsuario(userID)
    }

    suspend fun obtenerUsuarioDetalles(usuario: Int?): Usuario? {
        return repository.obtenerUsuarioDetalles(usuario)
    }

    suspend fun verificarUsuario(usuario: String, password: String): Int? {
        return  repository.verificarUsuario(usuario,password)
    }
}