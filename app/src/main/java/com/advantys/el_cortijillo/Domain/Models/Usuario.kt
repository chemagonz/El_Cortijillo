package com.advantys.el_cortijillo.Domain.Models

import com.advantys.el_cortijillo.Data.Database.Entities.Usuarios_Entity

class Usuario(

    var id: Int? = null,
    var nombre: String? = null,
    var direccion: String? = null,
    var telefono: String? = null,
    var correo: String? = null,
    var password: String? = null
)

fun Usuarios_Entity.toDomain() = Usuario(id, nombre, direccion, telefono, correo, password)