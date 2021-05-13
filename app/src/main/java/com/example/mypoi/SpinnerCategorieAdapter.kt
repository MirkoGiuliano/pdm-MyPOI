package com.example.mypoi

import android.app.Activity
import android.view.View
import android.widget.AdapterView

class SpinnerCategorieAdapter: Activity(), AdapterView.OnItemSelectedListener  {

    val db = DataBaseCategorie( this)

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        //QUA SI PASSA QUANDO PREMIAMO IL TASTO PER AGGIUNGERE UNA CATEGORIA
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
    }

}