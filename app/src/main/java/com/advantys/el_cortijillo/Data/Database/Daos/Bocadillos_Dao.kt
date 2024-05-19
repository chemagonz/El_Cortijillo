package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Bocadillos_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Bocadillos_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun obtenerBocadillos(): List<Bocadillos_Entity?> {
        val sql = "SELECT * FROM ${Bocadillos_Schema.TABLE_NAME} "
        return databaseManager.query(sql) { cursor ->
            Bocadillos_Entity.fromCursor(cursor)
        }
    }
}