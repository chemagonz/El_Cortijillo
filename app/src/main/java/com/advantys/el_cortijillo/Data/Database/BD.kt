package com.advantys.el_cortijillo.Data.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.os.Environment
import javax.inject.Inject

val DATABASE_NAME="CortijilloDef.db"
class BD @Inject constructor(contexto: Context): SQLiteOpenHelper(contexto, DATABASE_NAME, null, 1){

    private val DATABASE_RUTA = Environment.getExternalStorageDirectory().path+ "/ElCortijillo/"
    override fun onCreate(p0: SQLiteDatabase?) {
        TODO("Not yet implemented")
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
    fun openDatabaseRead(): SQLiteDatabase = openDatabase(SQLiteDatabase.OPEN_READONLY)
    fun openDatabaseWrite(): SQLiteDatabase = openDatabase(SQLiteDatabase.OPEN_READWRITE)

    fun openDatabase(modo:Int): SQLiteDatabase {

        try {
            val db= SQLiteDatabase.openDatabase("${DATABASE_RUTA}$DATABASE_NAME" , null, modo)
            db.rawQuery("PRAGMA journal_mode = DELETE", null)
            return db
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("Error al abrir la base de datos: $e")
        }
    }
    fun cerrarConexion(){
        if(this !=null) this.close()
    }
}