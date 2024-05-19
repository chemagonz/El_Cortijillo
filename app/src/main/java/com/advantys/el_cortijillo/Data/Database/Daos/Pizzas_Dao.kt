package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Pizzas_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Bocadillos_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Pizzas_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Pizzas_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun obtenerPizzas(): List<Pizzas_Entity?> {
        val sql = "SELECT * FROM ${Pizzas_Schema.TABLE_NAME} "
        return databaseManager.query(sql) { cursor ->
            Pizzas_Entity.fromCursor(cursor)
        }
    }
}