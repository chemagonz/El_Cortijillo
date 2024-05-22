package com.advantys.el_cortijillo.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Ingrediente
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Domain.UseCases.BocadillosPersonalizados_UseCase
import com.advantys.el_cortijillo.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BocadillosPersonalizados_ViewModel @Inject constructor(private val bocadillosPersonalizadosUsecase: BocadillosPersonalizados_UseCase): ViewModel() {

    val ingredientesModel = MutableLiveData<List<Ingrediente?>>()

    private var _mensajeRegistro = MutableLiveData<Respuesta>()
    val mensajeRegistro : LiveData<Respuesta> get() = _mensajeRegistro
    val bocadilloIdLiveData = MutableLiveData<Int>()


    fun obtenerIngredientes() {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = bocadillosPersonalizadosUsecase.obtenerIngredientes()
            if(!resultado.isNullOrEmpty()) ingredientesModel.postValue(resultado)
        }
    }


    fun registroBocadilloPersonalizado(bocadilloPersonalizado: BocadilloPersonalizado): LiveData<Long> {
        val bocadilloIdLiveData = MutableLiveData<Long>()
        viewModelScope.launch {
            try {
                val bocadilloId = bocadillosPersonalizadosUsecase.registroBocadillosPersonalizado(bocadilloPersonalizado)
                bocadilloIdLiveData.postValue(bocadilloId)
                _mensajeRegistro.postValue(Respuesta.ok("Bocadillo añadido correctamente"))
            } catch (e: Exception) {
                _mensajeRegistro.postValue(Respuesta.error("Error al añadir bocadillo"))
            }
        }

        return bocadilloIdLiveData
    }
}