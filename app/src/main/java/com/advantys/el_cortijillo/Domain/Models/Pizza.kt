package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Bocadillos_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Pizzas_Entity

class Pizza (

    var id: Int? = null,
    var nombre: String? = null,
    var descripcion: String? = null,
    var precio: Double? = null,
    var imagen: String? = null

)

fun Pizzas_Entity.toDomain() = Pizza(id, nombre, descripcion, precio, imagen)