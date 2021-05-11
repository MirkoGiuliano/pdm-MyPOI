package com.example.mypoi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList

class ListMyAdapter(private val context: Context, private val datiLista: ArrayList<DatoLista>) : BaseAdapter() {
    override fun getCount(): Int {
        return datiLista.size
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, converterView: View?, parent: ViewGroup?): View? {
        var newView = converterView

        if(converterView == null){
            newView = LayoutInflater.from(context).inflate(R.layout.row_list, parent, false)
        }

        if(newView != null){
           var categoria: TextView = newView.findViewById(R.id.categoria)
            var info_posizione: TextView = newView.findViewById(R.id.info_posizione)
            // se modifichiamo anche l'immagone della categoria aggiungere qui
            categoria.text = datiLista[position].getCategoria()
            info_posizione.text = datiLista[position].getInfo()

        }
        return newView
    }

}
