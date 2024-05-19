package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.Bebidas_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.RegistoUsuario_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Bebidas_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Domain.Models.Bebida
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class Bebidas_Repository @Inject constructor(private val bebidasDao: Bebidas_Dao) {

    suspend fun obtenerBebidas(): List<Bebida?> {
        val response: List<Bebidas_Entity?> = bebidasDao.obtenerBebidas()
        return response.filterNotNull().map { it.toDomain() }
    }

}