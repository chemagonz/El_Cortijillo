package com.advantys.el_cortijillo.Data.Database.Daos

import android.content.ContentValues
import com.advantys.el_cortijillo.Data.Database.Entities.Cesta_Entity
import com.advantys.el_cortijillo.Data.Database.Schemas.Cesta_Schema
import com.advantys.el_cortijillo.Utils.BDUtils
import javax.inject.Inject

class Cesta_Dao @Inject constructor(private val databaseManager: BDUtils) {

    fun insertCestaItems(usuarioID: Int?, productoID: Int? = null, bocadilloPersonalizadoID: Int? = null, cantidad: Int, nombreProducto: String? =  null, descripcion: String? = null,
                         precioProducto: Double? = null, imagenProducto: String? = null, nombreBocadillo:String? = null, ingredientesBocadillo: String? = null, precioBocadillo: Double? = null, imagenBocadillo:String? = null) {
        val contentValues = ContentValues().apply {
            put("UsuarioID", usuarioID)
            put("ProductoID", productoID)
            put("BocadilloPersonalizadoID", bocadilloPersonalizadoID)
            put("Cantidad", cantidad)
            put("NombreProducto", nombreProducto)
            put("DescripcionProducto", descripcion)
            put("PrecioProducto", precioProducto)
            put("ImagenProducto", imagenProducto)
            put("NombreBocadillo", nombreBocadillo)
            put("IngredientesBocadillo", ingredientesBocadillo)
            put("PrecioBocadillo", precioBocadillo)
            put("ImagenBocadillo", imagenBocadillo)


        }
        databaseManager.insert(Cesta_Schema.TABLE_NAME, contentValues)
    }

    fun obtenerItemsCesta(usuario: Int): List<Cesta_Entity?> {
        val sql = "SELECT * FROM ${Cesta_Schema.TABLE_NAME} WHERE ${Cesta_Schema.USUARIO_ID} = $usuario "
        return databaseManager.query(sql) { cursor ->
            Cesta_Entity.fromCursor(cursor)
        }
    }
}