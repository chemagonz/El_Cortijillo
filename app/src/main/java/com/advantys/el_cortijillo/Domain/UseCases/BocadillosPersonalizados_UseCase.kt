package com.advantys.el_cortijillo.Domain.UseCases


import com.advantys.el_cortijillo.Data.Repositories.BocadillosPersonalizados_Repository
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Ingrediente
import com.advantys.el_cortijillo.Domain.Models.Usuario
import javax.inject.Inject

class BocadillosPersonalizados_UseCase @Inject constructor(private val repository: BocadillosPersonalizados_Repository) {

    suspend  fun obtenerIngredientes(): List<Ingrediente?>{
        val ingrediente = repository.obtenerIngredientes()

        return if(ingrediente.isNullOrEmpty())
            listOf<Ingrediente>()
        else ingrediente
    }

    suspend fun registroBocadillosPersonalizado(bocadilloPersonalizado: BocadilloPersonalizado): Long {
      return  repository.registroBocadilloPersonalizado(bocadilloPersonalizado)
    }
}