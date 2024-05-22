package com.advantys.el_cortijillo.Data.Database.Entities

import android.content.ContentValues
import android.database.Cursor
import com.advantys.el_cortijillo.Data.Database.Schemas.BocadillosPersonalizados_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Cesta_Schema
import com.advantys.el_cortijillo.Data.Database.Schemas.Ingredientes_Schema
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Cesta
import com.advantys.el_cortijillo.Utils.getDouble
import com.advantys.el_cortijillo.Utils.getInt
import com.advantys.el_cortijillo.Utils.getString

class Cesta_Entity (

    var id: Int? = null,
    var usuarioID: Int? = null,
    var productoID: Int? = null,
    var bocadilloPersonalizadoID: Int? = null,
    var cantidad: Int? = null,
    var nombreProducto: String? = null,
    var descripcionProducto: String? = null,
    var precioProducto: Double? = null,
    var imagenProducto: String? = null,
    var nombreBocadillo: String? = null,
    var ingredientesBocadillo: String? = null,
    var precioBocadillo: Double? = null,
    var imagenBocadillo: String? = null
) {

    companion object {
        fun fromCursor(cursor: Cursor): Cesta_Entity {
            val modelo = Cesta_Entity()
            modelo.id = cursor.getInt(Cesta_Schema.CESTA_ID, null)
            modelo.usuarioID = cursor.getInt(Cesta_Schema.USUARIO_ID, null)
            modelo.productoID = cursor.getInt(Cesta_Schema.PRODUCTO_ID, null)
            modelo.bocadilloPersonalizadoID = cursor.getInt(Cesta_Schema.BOCADILLO_PERSONALIZADO_ID, null)
            modelo.cantidad = cursor.getInt(Cesta_Schema.CANTIDAD, null)
            modelo.nombreProducto = cursor.getString(Cesta_Schema.NOMBRE_PRODUCTO, null)
            modelo.descripcionProducto = cursor.getString(Cesta_Schema.DESCRIPCION_PRODUCTO, null)
            modelo.precioProducto = cursor.getDouble(Cesta_Schema.PRECIO_PRODUCTO, null)
            modelo.imagenProducto = cursor.getString(Cesta_Schema.IMAGEN_PRODUCTO, null)
            modelo.nombreBocadillo = cursor.getString(Cesta_Schema.NOMBRE_BOCADILLO, null)
            modelo.ingredientesBocadillo = cursor.getString(Cesta_Schema.INGREDIENTES_BOCADILLO, null)
            modelo.precioBocadillo = cursor.getDouble(Cesta_Schema.PRECIO_BOCADILLO, null)
            modelo.imagenBocadillo = cursor.getString(Cesta_Schema.IMAGEN_BOCADILLO, null)
            return modelo
        }
    }

//    fun toContentValues(): ContentValues {
//        val values= ContentValues()
//
//        values.put(Cesta_Schema.USUARIO_FIELD, usuarioID)
//        values.put(Cesta_Schema.PRODUCTO_FIELD, productoID)
//        values.put(Cesta_Schema.BOCADILLOPERSONALIZADO_FIELD, bocadilloPersonalizadoID)
//        values.put(Cesta_Schema.CANTIDAD_FIELD, cantidad)
//
//        return values
//    }
}

fun Cesta.toEntity() = Cesta_Entity(id, usuarioID, productoID, bocadilloPersonalizadoID, cantidad)


