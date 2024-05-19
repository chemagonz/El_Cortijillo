package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Bebidas_Repository
import com.advantys.el_cortijillo.Data.Repositories.Bocadillos_Repository
import com.advantys.el_cortijillo.Domain.Models.Bebida
import com.advantys.el_cortijillo.Domain.Models.Hamburguesa
import javax.inject.Inject

class Bebidas_UseCase  @Inject constructor(private val repository: Bebidas_Repository)  {

    suspend  fun obtenerBebidas(): List<Bebida?>{
        val bebida = repository.obtenerBebidas()

        return if(bebida.isNullOrEmpty())
            listOf<Bebida>()
        else bebida
    }
}