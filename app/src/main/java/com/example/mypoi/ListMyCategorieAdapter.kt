package com.example.mypoi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ListMyCategorieAdapter(private val context: Context, private val datiLista: MutableList<String> ) : BaseAdapter() {
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
            newView = LayoutInflater.from(context).inflate(R.layout.row_list_cat, parent, false)
        }

        if(newView != null){
            val categoria: TextView = newView.findViewById(R.id.textView_cat)
            categoria.text = datiLista[position]

        }

        return newView
    }
}