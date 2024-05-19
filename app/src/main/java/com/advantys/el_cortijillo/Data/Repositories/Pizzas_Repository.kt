package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.Pizzas_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.RegistoUsuario_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Pizzas_Entity
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.Pizza
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class Pizzas_Repository @Inject constructor(private val pizzasDao: Pizzas_Dao) {

    suspend fun obtenerPizzas(): List<Pizza?> {
        val response: List<Pizzas_Entity?> = pizzasDao.obtenerPizzas()
        return response.filterNotNull().map { it.toDomain() }
    }
}