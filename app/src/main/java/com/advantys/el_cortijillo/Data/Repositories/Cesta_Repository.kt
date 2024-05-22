package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.BocadillosPersonalizados_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.Cesta_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.toEntity
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Cesta
import javax.inject.Inject

class Cesta_Repository  @Inject constructor(private val cestaDao: Cesta_Dao) {

    fun insertCestaItems (usuarioID: Int?, productoID: Int? = null, bocadilloPersonalizadoID: Int? = null, cantidad: Int, nombreProducto: String? =  null, descripcion: String? = null,
                          precioProducto: Double? = null, imagenProducto: String? = null, nombreBocadillo:String? = null, ingredientesBocadillo: String? = null, precioBocadillo: Double? = null, imagenBocadillo:String? = null) {
        cestaDao.insertCestaItems(usuarioID, productoID, bocadilloPersonalizadoID, cantidad, nombreProducto, descripcion, precioProducto, imagenProducto, nombreBocadillo, ingredientesBocadillo, precioBocadillo, imagenBocadillo)
    }
}