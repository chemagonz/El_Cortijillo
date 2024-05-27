package com.advantys.el_cortijillo.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Cesta
import com.advantys.el_cortijillo.Domain.UseCases.Cesta_UseCase
import com.advantys.el_cortijillo.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Cesta_ViewModel @Inject constructor(private val cestaUsecase: Cesta_UseCase): ViewModel()  {

    private var _mensajeRegistro = MutableLiveData<Respuesta>()
    val mensajeRegistro : LiveData<Respuesta> get() = _mensajeRegistro

    val cestaModel: LiveData<List<Cesta?>>
        get() = _cestaModel

    private val _cestaModel = MutableLiveData<List<Cesta?>>()

    private var _usuarioID: Int? = null



    fun setUsuarioID(usuarioID: Int) {
        _usuarioID = usuarioID
    }


    fun obtenerItemsCesta(usuario: Int) {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = cestaUsecase.obtenerItemsCesta(usuario)
            if(!resultado.isNullOrEmpty()) _cestaModel.postValue(resultado)
        }
    }

    fun deleteCestaItem(cestaItemID: Int?) {
        viewModelScope.launch {
            try {
                cestaUsecase.deleteCestaItem(cestaItemID)
                _mensajeRegistro.postValue(Respuesta.ok("Ítem eliminado de la cesta"))
                _usuarioID?.let { obtenerItemsCesta(it) } // Actualiza la lista después de la eliminación
                _cestaModel.value = _cestaModel.value?.filter { it?.id != cestaItemID } // Actualiza la lista en el ViewModel
            } catch (e: Exception) {
                _mensajeRegistro.postValue(Respuesta.error("Error al eliminar el ítem de la cesta"))
            }
        }
    }

    fun deleteCesta() {
        viewModelScope.launch {
            cestaUsecase.deleteCesta()
        }
    }
}