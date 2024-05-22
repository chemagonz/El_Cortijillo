package com.advantys.el_cortijillo.UI.ViewModels

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.advantys.el_cortijillo.Domain.Models.Usuario
import com.advantys.el_cortijillo.Domain.UseCases.Usuario_UseCase
import com.advantys.el_cortijillo.Utils.Respuesta
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class Usuario_ViewModel @Inject constructor(private val usuarioUsecase: Usuario_UseCase): ViewModel() {

    private var _mensajeRegistro = MutableLiveData<Respuesta>()
    val mensajeRegistro : LiveData<Respuesta> get() = _mensajeRegistro

    val usuarioModel = MutableLiveData<Usuario?>()

    private val _userId = MutableLiveData<Int?>()
    val userId: LiveData<Int?> get() = _userId

    private val _nombreUsuario = MutableLiveData<String?>()
    val nombreUsuario: LiveData<String?> get() = _nombreUsuario


    fun registroUsuario(usuario: Usuario) {
        viewModelScope.launch {
            try {
                usuarioUsecase.registroUsuario(usuario)
                _mensajeRegistro.postValue(Respuesta.ok("Usuario registrado correctamente"))
            } catch (e: Exception) {
                _mensajeRegistro.postValue(Respuesta.error("Error al registrar usuario: ${e.message}"))
            }
        }
    }

    fun obtenerUsuarioDetalles(usuario: Int?) {
        viewModelScope.launch(Dispatchers.Default) {
            val resultado = usuarioUsecase.obtenerUsuarioDetalles(usuario)
            if (resultado != null) {
                usuarioModel.postValue(resultado)
            }
        }
    }

    fun verificarUsuario(usuario: String, contraseña: String){
        viewModelScope.launch {
            _userId.value = usuarioUsecase.verificarUsuario(usuario, contraseña)
        }
    }

    fun obtenerUsuario(userId: Int) {
        viewModelScope.launch {
            _nombreUsuario.value = usuarioUsecase.obtenerUsuario(userId)
        }
    }
}


