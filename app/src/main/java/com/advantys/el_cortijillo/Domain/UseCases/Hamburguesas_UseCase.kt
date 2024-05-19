package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Bocadillos_Repository
import com.advantys.el_cortijillo.Data.Repositories.Hamburguesas_Repository
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.Hamburguesa
import javax.inject.Inject

class Hamburguesas_UseCase  @Inject constructor(private val repository: Hamburguesas_Repository)  {

    suspend  fun obtenerHamburguesas(): List<Hamburguesa?>{
        val hamburguesa = repository.obtenerHamburguesas()

        return if(hamburguesa.isNullOrEmpty())
            listOf<Hamburguesa>()
        else hamburguesa
    }
}