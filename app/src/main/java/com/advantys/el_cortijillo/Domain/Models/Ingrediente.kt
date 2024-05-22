package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Ingredientes_Entity

class Ingrediente (

    var id: Int? = null,
    var nombre: String? = null,
) {
    override fun toString(): String {
        return "$id - $nombre"
    }
}


fun Ingredientes_Entity.toDomain() = Ingrediente(id, nombre)


