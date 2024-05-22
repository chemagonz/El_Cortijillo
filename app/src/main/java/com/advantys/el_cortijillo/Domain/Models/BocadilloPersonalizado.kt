package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.BocadillosPersonalizados_Entity

class BocadilloPersonalizado (

    var id: Int? = null,
    var nombre: String? = null,
    var ingrediente1: String? = null,
    var ingrediente2: String? = null,
    var ingrediente3: String? = null,
    var ingrediente4: String? = null,
    var ingrediente5: String? = null,
    var precio: Double? = null,
    var usuarioID: Int? = null
)

fun BocadillosPersonalizados_Entity.toDomain() = BocadilloPersonalizado(id, nombre, ingrediente1, ingrediente2, ingrediente3, ingrediente4, ingrediente5, precio, usuarioID)