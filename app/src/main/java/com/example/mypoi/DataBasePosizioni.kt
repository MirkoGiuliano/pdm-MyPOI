package com.example.mypoi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

private val TABLE_NAME = "posizioni"
private val COLONNA_CATEGORIE = "nomeCategoria"
private val COLONNA_DESCRIZIONE = "descrizione"
private val COLONNA_LATITUDINE = "latitudine"
private val COLONNA_LONGITUDINE = "longitudine"

class DataBasePosizioni(var context : Context) : SQLiteOpenHelper(context, "posizioni.db" , null , 1){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, $COLONNA_CATEGORIE VARCHAR(256), $COLONNA_DESCRIZIONE VARCHAR(256), $COLONNA_LATITUDINE VARCHAR(256), $COLONNA_LONGITUDINE VARCHAR(256) )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(data: DatoLista){
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLONNA_CATEGORIE,data.getCategoria())
        contentValues.put(COLONNA_DESCRIZIONE,data.getDescrizione())
        contentValues.put(COLONNA_LATITUDINE,data.getLatitudine())
        contentValues.put(COLONNA_LONGITUDINE,data.getLongitudine())
        val result = database.insert(TABLE_NAME , null ,contentValues)

        if(result == (0).toLong()){
            Toast.makeText(context, "inserimento fallito", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "inserimento avvenuto", Toast.LENGTH_SHORT).show()

        }
    }

    fun readData(): ArrayList<DatoLista>{
        val list: ArrayList<DatoLista> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do {
                var dato = DatoLista()
                dato.setCategoria(result.getString(result.getColumnIndex(COLONNA_CATEGORIE)))
                dato.setDescrizione(result.getString(result.getColumnIndex(COLONNA_DESCRIZIONE)))
                dato.setLatitudine(result.getString(result.getColumnIndex(COLONNA_LATITUDINE)))
                dato.setLongitudine(result.getString(result.getColumnIndex(COLONNA_LONGITUDINE)))
                list.add(dato)
            }while(result.moveToNext())
        }
        return list
    }

    fun updateData(posizione: Int, descrizione: String, categoria: String) {
        val db = this.readableDatabase
        val values = ContentValues()
        if(descrizione != ""){
            values.put(COLONNA_DESCRIZIONE, descrizione)
        }
        if(categoria != ""){
            values.put(COLONNA_CATEGORIE, categoria)
        }
        db.update(TABLE_NAME, values, "id = $posizione", arrayOf())
    }

    fun deleteData(posizione: Int){
        val db = this.readableDatabase
        db.delete(TABLE_NAME,"id = $posizione",null)
    }
}

