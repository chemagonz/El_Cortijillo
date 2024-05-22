package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Cesta_Repository
import com.advantys.el_cortijillo.Domain.Models.Cesta
import javax.inject.Inject

class Cesta_UseCase   @Inject constructor(private val repository: Cesta_Repository) {

    suspend fun insertCestaItems(usuarioID: Int?, productoID: Int? = null, bocadilloPersonalizadoID: Int? = null, cantidad: Int, nombreProducto: String? =  null, descripcion: String? = null,
                                 precioProducto: Double? = null, imagenProducto: String? = null, nombreBocadillo:String? = null, ingredientesBocadillo: String? = null, precioBocadillo: Double? = null, imagenBocadillo:String? = null) {
        repository.insertCestaItems(usuarioID, productoID, bocadilloPersonalizadoID, cantidad, nombreProducto, descripcion, precioProducto, imagenProducto, nombreBocadillo, ingredientesBocadillo, precioBocadillo, imagenBocadillo)
    }
}