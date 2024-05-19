package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Bocadillos_Repository
import com.advantys.el_cortijillo.Data.Repositories.Usuario_Repository
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import javax.inject.Inject

class Bocadillos_UseCase @Inject constructor(private val repository: Bocadillos_Repository) {

    suspend  fun obtenerBocadillos(): List<Bocadillo>{
        val bocadillo = repository.obtenerBocadillos()

        return if(bocadillo.isNullOrEmpty())
            listOf<Bocadillo>()
        else bocadillo
    }
}