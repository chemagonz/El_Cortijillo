package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Cesta_Entity

class Cesta (

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
)

fun Cesta_Entity.toDomain() = Cesta(id, usuarioID, productoID, bocadilloPersonalizadoID, cantidad, nombreProducto, descripcionProducto, precioProducto, imagenProducto, nombreBocadillo, ingredientesBocadillo, precioBocadillo, imagenBocadillo)