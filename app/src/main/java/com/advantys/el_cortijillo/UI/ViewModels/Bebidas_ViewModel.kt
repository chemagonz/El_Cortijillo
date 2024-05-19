package com.advantys.el_cortijillo.UI.ViewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Bebida
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.UseCases.Bebidas_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Bocadillos_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Bebidas_ViewModel @Inject constructor(private val bebidasUsecase: Bebidas_UseCase): ViewModel() {

    val bebidasModel = MutableLiveData<List<Bebida?>>()

    @SuppressLint("SuspiciousIndentation")
    fun obtenerBebidas() {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = bebidasUsecase.obtenerBebidas()
            if(!resultado.isNullOrEmpty()) bebidasModel.postValue(resultado)
        }
    }
}