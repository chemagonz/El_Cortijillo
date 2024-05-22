package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Productos_Entity

class Producto (

    var productoID: Int? = null,
    var categoria: String? = null,
    var itemID: Int? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var precio: Double? = null,
    var imagen: String? = null
)

fun Productos_Entity.toDomain() = Producto(productoID, categoria, itemID, nombre, descripcion, precio, imagen)