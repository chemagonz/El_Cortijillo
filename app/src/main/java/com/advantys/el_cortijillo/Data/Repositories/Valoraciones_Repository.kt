package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.Pedidos_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.Valoraciones_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Pedidos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Valoraciones_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.toEntity
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Valoracion
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class Valoraciones_Repository @Inject constructor(private val valoracionesDao: Valoraciones_Dao) {

    fun registroValoracion (valoracion: Valoracion) {
        valoracionesDao.registroValoracion(valoracion.toEntity())
    }

    suspend fun obtenerValoraciones(): List<Valoracion?> {
        val response: List<Valoraciones_Entity?> = valoracionesDao.obtenerValoraciones()
        return response.filterNotNull().map { it.toDomain() }
    }
}