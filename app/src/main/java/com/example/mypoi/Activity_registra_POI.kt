package com.example.mypoi

import android.app.Fragment
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.FragmentContainerView
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng


class Activity_registra_POI : AppCompatActivity(), MapsFragment.OnDataPass {

    lateinit var myPosition: LatLng
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registra_poi)

        val dbCat = DataBaseCategorie( this)
        dbCat.init()
        var categorie: MutableList<String> = dbCat.readData()
        val spinner_selettore_categoria: Spinner = findViewById(R.id.spinner_selettore_categoria)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorie)
        spinner_selettore_categoria.adapter = adapter

        val dbPos = DataBasePosizioni( this)

        val button_conferma: Button = findViewById(R.id.button_conferma)
        button_conferma.setOnClickListener{
            val dato = DatoLista()
            var editTestDescrizione: EditText = findViewById(R.id.editTextReg)
            dato.setDescrizione(editTestDescrizione.text.toString())
            dato.setCategoria(spinner_selettore_categoria.selectedItem.toString())
            dato.setLongitudine(myPosition.longitude.toString())
            dato.setLatitudine(myPosition.latitude.toString())
            dbPos.insertData(dato)
            finish()
        }
    }

    override fun onDataPass(data: LatLng) {
        myPosition = data
    }
}