package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Usuario_Repository
import com.advantys.el_cortijillo.Domain.Models.Usuario
import javax.inject.Inject

class Usuario_UseCase @Inject constructor(private val repository: Usuario_Repository) {

    suspend fun registroUsuario(usuario: Usuario) {
        repository.registroUsuario(usuario)
    }

    suspend fun obtenerUsuario(usuario: String, password: String): Usuario? {
      return  repository.obtenerUsuario(usuario,password)
    }
}