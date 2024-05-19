package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Bebidas_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity

class Bebida (

    var id: Int? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var precio: Double? = null,
    var imagen: String? = null

)

fun Bebidas_Entity.toDomain() = Bebida(id, nombre, descripcion, precio, imagen)