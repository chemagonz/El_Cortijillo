package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Pedidos_Entity

class Pedido (

    var id: Int? = null,
    var usuarioID: Int? = null,
    var total: String? = null,
    var fecha: String? = null,
    var horaRecogida: String? = null

)

fun Pedidos_Entity.toDomain() = Pedido(id, usuarioID, total, fecha, horaRecogida)