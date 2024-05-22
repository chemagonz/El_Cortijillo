package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.BocadillosPersonalizados_Dao

import com.advantys.el_cortijillo.Data.Database.Entities.Ingredientes_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.toEntity
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Ingrediente
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class BocadillosPersonalizados_Repository  @Inject constructor(private val bocadillospersonalizadosDao: BocadillosPersonalizados_Dao)  {

    suspend fun obtenerIngredientes(): List<Ingrediente?> {
        val response: List<Ingredientes_Entity?> = bocadillospersonalizadosDao.obtenerIngredientes()
        return response.filterNotNull().map { it.toDomain() }
    }

    fun registroBocadilloPersonalizado (bocadilloPersonalizado: BocadilloPersonalizado) : Long {
       return bocadillospersonalizadosDao.registroBocadillosPersonalizados(bocadilloPersonalizado.toEntity())
    }
}