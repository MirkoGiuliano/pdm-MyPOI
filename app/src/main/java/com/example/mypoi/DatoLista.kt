package com.example.mypoi

import android.widget.TextView

class DatoLista {
    private lateinit var categoria: String
    private lateinit var info: String

    fun getCategoria(): String {
        return categoria
    }

    fun getInfo(): String {
        return info
    }

    fun setCategoria(categoria: String) {
        this.categoria = categoria
    }

    fun setInfo(info: String){
        this.info = info
    }

}