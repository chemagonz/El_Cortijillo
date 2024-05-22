package com.advantys.el_cortijillo.Data.Database.Daos

import com.advantys.el_cortijillo.Data.Database.Entities.BocadillosPersonalizados_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Ingredientes_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Usuarios_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.BocadillosPersonalizados_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Ingredientes_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Usuario_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class BocadillosPersonalizados_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun obtenerIngredientes(): List<Ingredientes_Entity?> {
        val sql = "SELECT * FROM ${Ingredientes_Schema.TABLE_NAME}"
        return databaseManager.query(sql) { cursor ->
            Ingredientes_Entity.fromCursor(cursor)
        }
    }

    fun registroBocadillosPersonalizados(bocadilloPersonalizado: BocadillosPersonalizados_Entity): Long {
        return databaseManager.insertInt(BocadillosPersonalizados_Schema.TABLE_NAME, bocadilloPersonalizado.toContentValues())
    }

}