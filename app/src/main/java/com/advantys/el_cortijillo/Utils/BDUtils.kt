package com.advantys.el_cortijillo.Utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentValues
import android.database.Cursor
import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.advantys.el_cortijillo.Data.Database.BD
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

class BDUtils @Inject constructor (private val dbHelper: BD){
    fun insert(tabla: String, parametros : ContentValues) {
        val db = dbHelper.openDatabaseWrite()
        db.insert(tabla, null, parametros)
        db.close()
    }
    //    fun insertIfNotExists(tabla: String, parametros : ContentValues, where: String) {
//        if(!existe(tabla, where))
//            insert(tabla, parametros)
//    }
    fun update(tabla: String, parametros : ContentValues, where: String) {
        val db = dbHelper.openDatabaseWrite()
        db.update(tabla, parametros, where, null)
        db.close()
    }
    //    fun upsert(tabla: String, parametros : ContentValues, where: String) {
//        if(existe(tabla, where))
//            update(tabla, parametros, where)
//        else insert(tabla, parametros)
//    }
    fun delete(tabla: String):Boolean {
        try {
            val db = dbHelper.openDatabaseWrite()
            db.delete(tabla, null, null)
            db.close()
            return true
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }
    }
    fun delete(tabla: String, where: String) :Boolean{
        try {
            val db = dbHelper.openDatabaseWrite()
            db.delete(tabla, where, null)
            db.close()
            return true
        }catch (e:Exception){
            e.printStackTrace()
            return false
        }
    }


    fun existe(tabla: String, where: String): Boolean {
        val db = dbHelper.openDatabaseRead()
        val selectQuery = "SELECT COUNT(*) FROM ${tabla} WHERE ${where}"
        val cursor = db.rawQuery(selectQuery, null)
        var resultado = false

        try {
            if (cursor.count != 0) {
                if (cursor.count > 0) resultado = true
            }
        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return resultado
    }

    fun existeInt(tabla: String, where: String): Int {
        val db = dbHelper.openDatabaseRead()
        val selectQuery = " SELECT COUNT(*)  FROM $tabla WHERE $where "
        val cursor = db.rawQuery(selectQuery, null)
        var count = 0

        try {
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.close()
            db.close()
        }

        return count
    }

    //Funcion generica para mostrar una lista    04/03/2024
    fun <T> query(sql: String, fromCursor: (cursor: Cursor) -> T): List<T>{
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        val lista: MutableList<T> = arrayListOf()

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    lista.add(fromCursor(cursor))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }
        return lista
    }
    fun queryUp(sql:String){
        val db = dbHelper.openDatabaseWrite()
        try{
            db.execSQL(sql)
        }catch (e:Exception){
            e.printStackTrace()
        }finally{
            db.close()
        }
    }

    fun queryInsert(sql:String){
        val db = dbHelper.openDatabaseWrite()
        try{
            db.execSQL(sql)
        }catch (e:Exception){
            e.printStackTrace()
        }finally{
            db.close();
        }
    }
    fun <T> queryDetalles(sql:String, fromCursor:(cursor: Cursor)->T):T?{
        val db = dbHelper.openDatabaseRead()
        val cursor: Cursor = db.rawQuery(sql, null)
        var result: T? = null
        try{
            if (cursor!=null&&cursor.moveToNext()){
                result=fromCursor(cursor)
            }
        }catch(e:Exception){
            e.printStackTrace()
        }finally {
            cursor?.close()
            db.close()
        }
        return result
    }

    ///region  Utilidades
    //fun Any?.esNulo() = this == null

    fun Cursor.cerrar(){
        if(!this.esNulo()) this.close()
    }
    fun Cursor.isEmpty() : Boolean {
        var resultado = true
        if(!this.esNulo()) {
            if (this.count > 0){
                this.moveToFirst()
                resultado = false
            }
        }
        return resultado
    }
    ///region Funciones SelectScalar

    //Función nueva de prueba
    fun getScalInt(sql: String): Int {
        var db = dbHelper.openDatabaseRead()
        val cursor = db.rawQuery(sql, null)
        var resultado: Int = 0

        try {
            if (cursor.moveToFirst()) {
                resultado = cursor.getInt(0)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor.close()
            db.close()
        }

        return resultado
    }
    fun getSelectScalarInt(sql: String) :Int {
        return getSelectScalar(sql) as Int
    }

    fun getSelectScalarBoolean(sql: String) :Boolean {
        return getSelectScalar(sql) as Boolean
    }

    fun getSelectScalarString(sql: String) :String {
        return getSelectScalar(sql) as String
    }

    fun getSelectScalarFloat(sql: String) :Float {
        return getSelectScalar(sql) as Float
    }

    fun getSelectScalar(sql: String): Any? {
        var db = dbHelper.openDatabaseRead()
        val cursor = db.rawQuery(sql, null)
        var resultado: Any? = null

        try {
            if (!cursor.isEmpty()) {
                resultado = cursor.getColumnIndex(cursor.getColumnName(1))
            }
        }catch (e:Exception){
            e.printStackTrace()
        } finally {
            cursor.cerrar()
            db.close()
        }

        return resultado
    }
    fun Cursor.getBooleanInt(column: Int) : Boolean {
        var resultado = true
        if(!this.esNulo()) {
            if (this.count > 0){
                this.moveToFirst()
                resultado = false
            }
        }
        return resultado
    }
    fun cerrarConexionDB(db: BD){

    }
    fun cerrar(cursor: Cursor, db: BD){
        db.cerrarConexion();

    }
    private fun CrearBBDD():Boolean{
        var result= false
        //FUNCIÓN VACÍA POR AHORA
        return result
    }

    fun actualizarBD():Boolean{
        var result= true
        var query = ArrayList<String>()
        query.add("")
        query.add("")
        query.add("")
        query.add("")

        return result
    }
    //Funcion para ocultar teclado
    object KeyboardUtil {
        fun esconderTeclado(activity: Activity) {
            activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }
}
    @SuppressLint("Range")
    fun Cursor.esNulocolumn(nombreColumna:String):Boolean{
        return this.isNull(this.getColumnIndex(nombreColumna))
    }

    fun Cursor.getInt(nombreColumna: String):Int?{
        return this.getInt(nombreColumna, null)
    }

    @SuppressLint("Range")
    fun Cursor.getString(nombreColumna: String): String?{
        if(this.esNulocolumn(nombreColumna)) return null
        else return this.getString(this.getColumnIndex(nombreColumna))
    }
    @SuppressLint("Range")
    fun Cursor.getShort(nombreColumna:String):Short?{
        if(this.esNulocolumn(nombreColumna)) return null
        else return this.getShort(this.getColumnIndex(nombreColumna))
    }
    @SuppressLint("Range")
    fun Cursor.getInt(nombreColumna: String, valorDefecto: Int?):Int?{
        if(this.esNulocolumn(nombreColumna)) return valorDefecto
        else return this.getInt(this.getColumnIndex(nombreColumna))
    }
    @SuppressLint("Range")
    fun Cursor.getString(nombreColumna: String, valorDefecto: String?):String?{
        if(this.esNulocolumn(nombreColumna)) return valorDefecto
        else return this.getString(this.getColumnIndex(nombreColumna))
    }
    @SuppressLint("Range")
    fun Cursor.getShort(nombreColumna:String, valorDefecto:Short?):Short?{
        if (this.esNulocolumn(nombreColumna)) return valorDefecto
        else return this.getShort(this.getColumnIndex(nombreColumna))
    }
    @SuppressLint("Range")
    fun Cursor.getBoolean(nombreColumna: String, valorDefecto: Boolean?): Boolean? {
        if (this.esNulocolumn(nombreColumna)) return valorDefecto
        else {
            if(this.getInt(this.getColumnIndex(nombreColumna))==0) return  false
            else return true
        }
    }
    @SuppressLint("Range")
    fun Cursor.getFloat(nombreColumna:String, valorDefecto:Float?):Float?{
        if (this.esNulocolumn(nombreColumna)) return valorDefecto
        else return this.getFloat(this.getColumnIndex(nombreColumna))
    }
    @SuppressLint("Range")
    fun Cursor.getDouble(nombreColumna:String, valorDefecto:Double?):Double?{
        if (this.esNulocolumn(nombreColumna)) return valorDefecto
        else return this.getDouble(this.getColumnIndex(nombreColumna))
    }

    @SuppressLint("Range")
    fun Cursor.getDate(nombreColumna: String, valorDefecto: Date?): Date? {
        if (this.esNulocolumn(nombreColumna)) return valorDefecto
        else {
            val fechaString = this.getString(this.getColumnIndex(nombreColumna))
            val formatoFecha = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return formatoFecha.parse(fechaString)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun Cursor.getLocalDateTime(nombreColumna: String, valorDefecto: LocalDateTime?): LocalDateTime? {
        val columnIndex = this.getColumnIndex(nombreColumna)
        if (columnIndex == -1 || this.isNull(columnIndex)) {
            return valorDefecto
        } else {
            val fechaString = this.getString(columnIndex)
            val formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            return LocalDateTime.parse(fechaString, formatoFecha)
        }
    }


