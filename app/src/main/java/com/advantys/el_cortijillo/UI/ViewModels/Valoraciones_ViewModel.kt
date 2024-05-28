package com.advantys.el_cortijillo.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Pedido
import com.advantys.el_cortijillo.Domain.Models.Valoracion
import com.advantys.el_cortijillo.Domain.UseCases.Pedido_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Valoracion_UseCase
import com.advantys.el_cortijillo.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class Valoraciones_ViewModel @Inject constructor(private val valoracionUsecase: Valoracion_UseCase): ViewModel() {

    val valoracionModel = MutableLiveData<List<Valoracion?>>()

    private var _mensajeRegistro = MutableLiveData<Respuesta>()
    val mensajeRegistro : LiveData<Respuesta> get() = _mensajeRegistro

    fun registroValoracion(valoracion: Valoracion) {
        viewModelScope.launch {
            try {
                valoracionUsecase.registroValoracion(valoracion)
                _mensajeRegistro.postValue(Respuesta.ok("Valoración insertada con éxito"))

                // Actualizar la lista de valoraciones después de registrar una nueva
                val currentList = valoracionModel.value?.toMutableList() ?: mutableListOf()
                currentList.add(valoracion)
                valoracionModel.postValue(currentList)
            } catch (e: Exception) {
                _mensajeRegistro.postValue(Respuesta.error("Error al realizar la valoración: ${e.message}"))
            }
        }
    }

    fun obtenerValoraciones() {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = valoracionUsecase.obtenerValoraciones()
            if (!resultado.isNullOrEmpty()) valoracionModel.postValue(resultado)
        }
    }
}