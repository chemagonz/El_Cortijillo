package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.Pedidos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Valoraciones_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Pedidos_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Valoraciones_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Valoraciones_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun registroValoracion(valoraciones: Valoraciones_Entity) {
        databaseManager.insert(Valoraciones_Schema.TABLE_NAME, valoraciones.toContentValues())
    }

    fun obtenerValoraciones(): List<Valoraciones_Entity> {
        val sql = "SELECT * FROM ${Valoraciones_Schema.TABLE_NAME} "
        return  databaseManager.query(sql) { cursor ->
            Valoraciones_Entity.fromCursor(cursor)
        }
    }
}