package com.advantys.el_cortijillo.UI.ViewModels

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Bocadillo
import com.advantys.el_cortijillo.Domain.UseCases.Bocadillos_UseCase
import com.advantys.el_cortijillo.Domain.UseCases.Usuario_UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Bocadillos_ViewModel @Inject constructor(private val bocadilloUsecase: Bocadillos_UseCase): ViewModel() {

    val bocadillosModel = MutableLiveData<List<Bocadillo>>()

    @SuppressLint("SuspiciousIndentation")
    fun obtenerBocadillos() {
        viewModelScope.launch(Dispatchers.Default) {

          val resultado = bocadilloUsecase.obtenerBocadillos()
            if(!resultado.isNullOrEmpty()) bocadillosModel.postValue(resultado)
        }
    }
}