package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.Bebidas_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Bebidas_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Bocadillos_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Bebidas_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun obtenerBebidas(): List<Bebidas_Entity?> {
        val sql = "SELECT * FROM ${Bebidas_Schema.TABLE_NAME} "
        return databaseManager.query(sql) { cursor ->
            Bebidas_Entity.fromCursor(cursor)
        }
    }
}