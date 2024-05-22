package com.advantys.el_cortijillo.Data.Repositories

import com.advantys.el_cortijillo.Data.Database.Daos.Pizzas_Dao
import com.advantys.el_cortijillo.Data.Database.Daos.Productos_Dao
import com.advantys.el_cortijillo.Data.Database.Entities.Hamburguesas_Entity
import com.advantys.el_cortijillo.Data.Database.Entities.Productos_Entity
import com.advantys.el_cortijillo.Domain.Models.Hamburguesa
import com.advantys.el_cortijillo.Domain.Models.Producto
import com.advantys.el_cortijillo.Domain.Models.toDomain
import javax.inject.Inject

class Productos_Repository @Inject constructor(private val productosDao: Productos_Dao) {

    suspend fun obtenerProductos(filtro: String): List<Producto?> {
        val response: List<Productos_Entity?> = productosDao.obtenerProductos(filtro)
        return response.filterNotNull().map { it.toDomain() }
    }

    suspend fun obtenerProductosCategoria(categoria: String?): List<Producto?> {
        val response: List<Productos_Entity?> = productosDao.obtenerProductoCategoria(categoria)
        return response.filterNotNull().map { it.toDomain() }
    }
    suspend fun obtenerProductosDetalles(categoria: String?, id: Int): Producto? {
        val response: Productos_Entity? = productosDao.obtenerProductosDetalle(categoria, id)
        return response?.toDomain()
    }
}