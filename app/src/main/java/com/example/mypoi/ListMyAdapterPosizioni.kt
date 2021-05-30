package com.example.mypoi

import android.content.Context
import android.content.Intent
import android.os.Build
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class ListMyAdapterPosizioni(private val context: Context, private val datiLista: ArrayList<DatoLista>) : BaseAdapter() {
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
            categoria.text = datiLista[position].getCategoria()
            info_posizione.text = datiLista[position].getDescrizione()

        }

        var buttonModifica: ImageButton = newView!!.findViewById(R.id.buttonModifica)

        buttonModifica.setOnClickListener{

            val inflater:LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.activity_modifica_poi,null)
            val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true)

            val dbCat = DataBaseCategorie(context)
            dbCat.init()
            var categorie: MutableList<String> = dbCat.readData()
            val spinner_selettore_categoria: Spinner = view.findViewById(R.id.spinner_selettore_categoria_modifica)
            val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, categorie)
            spinner_selettore_categoria.adapter = adapter
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                popupWindow.elevation = 10.0F
            }

            
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                val slideIn = Slide()
                slideIn.slideEdge = Gravity.TOP
                popupWindow.enterTransition = slideIn
                
                val slideOut = Slide()
                slideOut.slideEdge = Gravity.RIGHT
                popupWindow.exitTransition = slideOut

            }
            
            val buttonElimina = view.findViewById<Button>(R.id.buttonElimina)
            val buttonSalva = view.findViewById<Button>(R.id.buttonSalva)
            val dbPos = DataBasePosizioni(context)

            buttonElimina.setOnClickListener{
                popupWindow.dismiss()
                Toast.makeText(context,"Posizione eliminata",Toast.LENGTH_SHORT).show()
                dbPos.deleteData(position)
            }
            buttonSalva.setOnClickListener{
                val et = view.findViewById<EditText>(R.id.editTextDescrizione_modifica)
                if("" != et.text.toString()){
                    dbPos.updateData(position, et.text.toString(), spinner_selettore_categoria.selectedItem.toString())
                    popupWindow.dismiss()
                    Toast.makeText(context,"Posizione modificata",Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(context,"Descrizione posizione vuota",Toast.LENGTH_SHORT).show()
                }
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                TransitionManager.beginDelayedTransition(parent)
            }
            popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0)
        }

        var linearToClick: LinearLayout = newView!!.findViewById(R.id.linear_to_click)
        linearToClick.setOnClickListener {
            StartActivityConPosizione(position)
        }

        var immagineCategoria: ImageView = newView!!.findViewById(R.id.immagineCategoria)
        immagineCategoria.setOnClickListener{
            StartActivityConPosizione(position)
        }
        return newView
    }

    private fun StartActivityConPosizione(position: Int) {
        val intent = Intent(context, MapShowPosizione::class.java)
        val dbPos = DataBasePosizioni(context)
        val latLon = dbPos.getLonLanAtPos(position)
        intent.putExtra("lon", latLon[1])
        intent.putExtra("lat", latLon[0])
        startActivity(context, intent, null)
    }

}
