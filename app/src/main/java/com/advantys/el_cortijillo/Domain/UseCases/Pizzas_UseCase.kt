package com.advantys.el_cortijillo.Domain.UseCases

import com.advantys.el_cortijillo.Data.Repositories.Bocadillos_Repository
import com.advantys.el_cortijillo.Data.Repositories.Pizzas_Repository
import com.advantys.el_cortijillo.Domain.Models.Bebida
import com.advantys.el_cortijillo.Domain.Models.Pizza
import javax.inject.Inject

class Pizzas_UseCase  @Inject constructor(private val repository: Pizzas_Repository)  {

    suspend  fun obtenerPizzas(): List<Pizza?>{
        val pizza = repository.obtenerPizzas()

        return if(pizza.isNullOrEmpty())
            listOf<Pizza>()
        else pizza
    }
}