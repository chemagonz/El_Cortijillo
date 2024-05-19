package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.Hamburguesas_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.RegistoUsuario_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Hamburguesas_Entity
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.Hamburguesa
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class Hamburguesas_Repository @Inject constructor(private val hamburguesaDao: Hamburguesas_Dao) {

    suspend fun obtenerHamburguesas(): List<Hamburguesa?> {
        val response: List<Hamburguesas_Entity?> = hamburguesaDao.obtenerHamburguesas()
        return response.filterNotNull().map { it.toDomain() }
    }
}