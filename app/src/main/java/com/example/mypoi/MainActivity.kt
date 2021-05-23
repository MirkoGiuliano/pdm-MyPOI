package com.example.mypoi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
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
        updateLista()
    }

    override fun onResume() {
        super.onResume()
        updateLista()
    }

    private fun updateLista(){
        val dbPos = DataBasePosizioni( this)
        var datiLista = dbPos.readData()
        listaPosizioni.adapter = ListMyAdapter(this,datiLista)
    }



}

