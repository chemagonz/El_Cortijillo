package com.advantys.el_cortijillo.UI.ViewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.Pizza
import com.advantys.el_cortijillo.Domain.UseCases.Bocadillos_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Pizzas_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Pizzas_ViewModel  @Inject constructor(private val pizzasUsecase: Pizzas_UseCase): ViewModel() {

    val pizzasModel = MutableLiveData<List<Pizza?>>()

    @SuppressLint("SuspiciousIndentation")
    fun obtenerPizzas() {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = pizzasUsecase.obtenerPizzas()
            if(!resultado.isNullOrEmpty()) pizzasModel.postValue(resultado)
        }
    }
}