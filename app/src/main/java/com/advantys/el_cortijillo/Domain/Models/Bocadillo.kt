package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity

class Bocadillo (

    var id: Int? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var precio: Double? = null,
    var imagen: String? = null

)

fun Bocadillos_Entity.toDomain() = Bocadillo(id, nombre, descripcion, precio, imagen)