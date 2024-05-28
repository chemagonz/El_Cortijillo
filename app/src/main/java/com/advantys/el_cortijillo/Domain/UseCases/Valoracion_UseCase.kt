package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Pedidos_Repository
import com.advantys.el_cortijillo.Data.Repositories.Valoraciones_Repository
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Valoracion
import javax.inject.Inject

class Valoracion_UseCase  @Inject constructor(private val repository: Valoraciones_Repository)  {

    fun registroValoracion(valoracion: Valoracion) {
        repository.registroValoracion(valoracion)
    }

    suspend  fun obtenerValoraciones(): List<Valoracion?>{
        val valoracion = repository.obtenerValoraciones()

        return if(valoracion.isNullOrEmpty())
            listOf<Valoracion>()
        else valoracion
    }
}