package com.example.mypoi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

private val TABLE_NAME = "categorie"
private val COLONNA_CATEGORIE = "nomeCategoria"

class DataBaseCategorie(var context : Context) : SQLiteOpenHelper(context, "categorie.db" , null , 1){

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE $TABLE_NAME (id INTEGER PRIMARY KEY AUTOINCREMENT, $COLONNA_CATEGORIE VARCHAR(256) )")
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        TODO("Not yet implemented")
    }

    fun insertData(categoria: String){
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLONNA_CATEGORIE,categoria)
        database.insert(TABLE_NAME , null ,contentValues)
    }

    fun readData(): MutableList<String>{
        val list: MutableList<String> = ArrayList()
        val db = this.readableDatabase
        val query = "Select $COLONNA_CATEGORIE from $TABLE_NAME"
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do {
                list.add(result.getString(result.getColumnIndex(COLONNA_CATEGORIE)))
            }while(result.moveToNext())
        }
        return list
    }

    fun init() {
        val db = this.readableDatabase
        val query = "Select Count(*) from $TABLE_NAME"
        val result = db.rawQuery(query, null)
        if(result.moveToFirst() && result.getInt(0) < 1){
            insertData("Parcheggio")
            insertData("Monumento")
            insertData("Bar")
            insertData("Ristorante")
            insertData("Da visitare")
        }
    }

    fun updateData(position: Int, nomeCategoria: String) {
        val db = this.readableDatabase
        val values = ContentValues()
        values.put(COLONNA_CATEGORIE, nomeCategoria)
        val ourID = getID(position)
        db.update(TABLE_NAME, values, "id = $ourID", arrayOf())
    }

    fun getID(posizione: Int): String {
        val db = this.readableDatabase
        val query = "SELECT id FROM $TABLE_NAME LIMIT 1 OFFSET $posizione"
        val result = db.rawQuery(query, null)
        result.moveToFirst()
        return result.getString(result.getColumnIndex("id"))
    }

    fun deleteData(posizione: Int) {
        val db = this.readableDatabase
        val ourID = getID(posizione)
        db.delete(TABLE_NAME,"id = $ourID",null)
    }

}
