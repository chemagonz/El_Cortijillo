package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Hamburguesas_Repository
import com.advantys.el_cortijillo.Data.Repositories.Productos_Repository
import com.advantys.el_cortijillo.Domain.Models.Bebida
import com.advantys.el_cortijillo.Domain.Models.Producto
import javax.inject.Inject

class Producto_UseCase  @Inject constructor(private val repository: Productos_Repository) {

    suspend  fun obtenerProductos(filtro: String): List<Producto?>{
        val bebida = repository.obtenerProductos(filtro)

        return if(bebida.isNullOrEmpty())
            listOf<Producto>()
        else bebida
    }

    suspend  fun obtenerProductosCategoria(categoria: String?): List<Producto?>{
        val bebida = repository.obtenerProductosCategoria(categoria)

        return if(bebida.isNullOrEmpty())
            listOf<Producto>()
        else bebida
    }

    suspend fun obtenerProductosDetalles(categoria: String?, id: Int): Producto? {
        return repository.obtenerProductosDetalles(categoria,id)
    }

}