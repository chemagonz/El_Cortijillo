package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Hamburguesas_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Bocadillos_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Hamburguesas_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Hamburguesas_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun obtenerHamburguesas(): List<Hamburguesas_Entity?> {
        val sql = "SELECT * FROM ${Hamburguesas_Schema.TABLE_NAME} "
        return databaseManager.query(sql) { cursor ->
            Hamburguesas_Entity.fromCursor(cursor)
        }
    }
}