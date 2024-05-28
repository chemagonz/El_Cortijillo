package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Pedidos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Valoraciones_Entity

class Valoracion (

    var id: Int? = null,
    var usuarioID: Int? = null,
    var valoracion: String? = null,
    var fecha: String? = null,
    var nombreUsuario: String? = null
)

fun Valoraciones_Entity.toDomain() = Valoracion(id, usuarioID, valoracion, fecha, nombreUsuario)


