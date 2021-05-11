package com.example.mypoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import com.example.mypoi.DatoLista as DatoLista

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var datiLista = ArrayList<DatoLista>()
        // qua dovra cercare i dati sul database
        listaPosizioni.adapter = ListMyAdapter(this,datiLista)
    }

}

