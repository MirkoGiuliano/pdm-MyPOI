package com.example.mypoi

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val TABLE_NAME = "categorie"
val COLONNA_CATEGORIE = "nomeCategoria"
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
        val result = database.insert( TABLE_NAME , null ,contentValues)
        if(result == (0).toLong()){
            Toast.makeText(context, "inserimento categoria fallito", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context, "inserimento categoria avvenuto", Toast.LENGTH_SHORT).show()

        }
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

}
