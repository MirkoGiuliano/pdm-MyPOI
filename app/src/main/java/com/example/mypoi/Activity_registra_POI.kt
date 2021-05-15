package com.example.mypoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner


class Activity_registra_POI : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registra_poi)

        val db = DataBaseCategorie( this)
        var categorie: MutableList<String> = db.readData()

        val spinner_selettore_categoria: Spinner = findViewById(R.id.spinner_selettore_categoria)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorie)
        spinner_selettore_categoria.adapter = adapter
    }
}