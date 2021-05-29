package com.example.mypoi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_modifica_categorie.*

class ModificaCategorieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modifica_categorie)
        var posizione = 999;

        lista_categorie.setOnItemClickListener { parent, view, position, id ->
            var categoriaSelected: TextView = view.findViewById(R.id.textView_cat)
            this.findViewById<EditText>(R.id.editTextModificaCat).setText(categoriaSelected.text.toString())
            posizione = position
        }

        buttonSalvaCat.setOnClickListener{
            if(posizione != 999){
                val dbCat = DataBaseCategorie(this)
                var categoriaUpdated: TextView = this.findViewById(R.id.editTextModificaCat)
                dbCat.updateData(posizione ,categoriaUpdated.text.toString())
                Toast.makeText(this, "categoria aggiornata", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "nessuna categoria selezionata", Toast.LENGTH_SHORT).show()
            }
            updateLista()
        }

        buttonEliminaCat.setOnClickListener{
            if(posizione != 999){
                val dbCat = DataBaseCategorie(this)
                dbCat.deleteData(posizione)
                Toast.makeText(this, "categoria aggiornata", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "nessuna categoria selezionata", Toast.LENGTH_SHORT).show()
            }
            updateLista()
        }
        updateLista()
    }

    private fun updateLista(){
        val dbCat = DataBaseCategorie( this)
        var list:MutableList<String>  = dbCat.readData()
        lista_categorie.adapter = ListMyCategorieAdapter(this,list)
    }

    override fun onResume() {
        super.onResume()
        updateLista()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        updateLista()
    }

}
