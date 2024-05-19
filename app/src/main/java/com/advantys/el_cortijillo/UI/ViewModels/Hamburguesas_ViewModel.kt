package com.advantys.el_cortijillo.UI.ViewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.Models.Hamburguesa
import com.advantys.el_cortijillo.Domain.UseCases.Bocadillos_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Hamburguesas_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Hamburguesas_ViewModel @Inject constructor(private val hamburguesasUsecase: Hamburguesas_UseCase): ViewModel() {

    val hamburguesasModel = MutableLiveData<List<Hamburguesa?>>()

    @SuppressLint("SuspiciousIndentation")
    fun obtenerHamburguesas() {
        viewModelScope.launch(Dispatchers.Default) {

            val resultado = hamburguesasUsecase.obtenerHamburguesas()
            if(!resultado.isNullOrEmpty()) hamburguesasModel.postValue(resultado)
        }
    }
}