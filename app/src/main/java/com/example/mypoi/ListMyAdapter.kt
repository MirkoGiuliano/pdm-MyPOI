package com.example.mypoi

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.ArrayList

class ListMyAdapter(private val context: Context, private val datiLista: ArrayList<DatoLista>) : BaseAdapter() {
    override fun getCount(): Int {
        return datiLista.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

}
