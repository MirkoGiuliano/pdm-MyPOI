package com.example.mypoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import com.example.mypoi.DatoLista as DatoLista

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val add_new_posizione: FloatingActionButton = findViewById(R.id.add_new_posizione)
        add_new_posizione.setOnClickListener{
            val intent = Intent(this, Activity_registra_POI::class.java)
                    startActivity(intent)
        }
        var datiLista = ArrayList<DatoLista>()
        // qua dovra cercare i dati sul database
        listaPosizioni.adapter = ListMyAdapter(this,datiLista)
    }

}

