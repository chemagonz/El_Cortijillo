package com.advantys.el_cortijillo.UI.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.BocadilloPersonalizado
import com.advantys.el_cortijillo.Domain.Models.Cesta
import com.advantys.el_cortijillo.Domain.UseCases.BocadillosPersonalizados_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Cesta_UseCase
import com.advantys.el_cortijillo.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Cesta_ViewModel @Inject constructor(private val cestaUsecase: Cesta_UseCase): ViewModel()  {

    private var _mensajeRegistro = MutableLiveData<Respuesta>()
    val mensajeRegistro : LiveData<Respuesta> get() = _mensajeRegistro

//    fun insertCestaItems(cesta: Cesta) {
//        viewModelScope.launch {
//            try {
//                cestaUsecase.insertCestaItems(cesta)
//                _mensajeRegistro.postValue(Respuesta.ok("Añadido con éxito a la cests"))
//            } catch (e: Exception) {
//                _mensajeRegistro.postValue(Respuesta.error("Error al añadir a la cesta"))
//            }
//        }
//    }
}