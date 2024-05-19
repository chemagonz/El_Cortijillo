package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.Bocadillos_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.RegistoUsuario_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class Bocadillos_Repository  @Inject constructor(private val bocadillosDao: Bocadillos_Dao) {

    suspend fun obtenerBocadillos(): List<Bocadillo> {
        val response: List<Bocadillos_Entity?> = bocadillosDao.obtenerBocadillos()
        return response.filterNotNull().map { it.toDomain() }
    }
}