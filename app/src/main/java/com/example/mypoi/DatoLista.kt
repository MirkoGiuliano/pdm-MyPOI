package com.example.mypoi

class DatoLista {
    private lateinit var categoria: String
    private lateinit var descrizione: String
    private lateinit var latitudine: String
    private lateinit var longitudine: String

    fun getCategoria(): String {
        return categoria
    }

    fun getDescrizione(): String {
        return descrizione
    }

    fun setCategoria(categoria: String) {
        this.categoria = categoria
    }

    fun setDescrizione(descrizione: String){
        this.descrizione = descrizione
    }

    fun getLatitudine(): String {
        return latitudine
    }

    fun getLongitudine(): String {
        return longitudine
    }

    fun setLatitudine(latitudine: String) {
        this.latitudine = latitudine
    }

    fun setLongitudine(longitudine: String){
        this.longitudine = longitudine
    }

}