package com.advantys.el_cortijillo.Utils

data class Respuesta(

    val tipo: Tipo,
    val mensaje: String
){
    enum class Tipo{
        OK,
        ERROR,
        CARGANDO
    }

    companion object{
        fun ok(mensaje: String): Respuesta {
            return Respuesta(Tipo.OK, mensaje)
        }

        fun error(mensaje : String): Respuesta {
            return Respuesta(Tipo.ERROR, mensaje)
        }

        fun cargando(mensaje : String):Respuesta{
            return Respuesta(Tipo.CARGANDO,mensaje)
        }
    }
}