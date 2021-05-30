package com.example.mypoi

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

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
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setMessage("- Per Inserire una nuova posizione da salvare premi il pulsante in basso a destra!" +
        "\n-Per Aggiungere o modificare una categoria premi il menu in alto a destra")
            .setCancelable(false)
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            .setTitle("Istruzioni")
        val alert: AlertDialog = builder.create()
        alert.show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.modificaCategorie -> {
                val intent = Intent(this, ModificaCategorieActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.aggiungiCategorie -> {
                val inflater: LayoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                val view = inflater.inflate(R.layout.activity_aggiungi_categoria,null)
                val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true)

                val dbCat = DataBaseCategorie(this)
                dbCat.init()

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

                val buttonSalva = view.findViewById<Button>(R.id.buttonSalvaCatAdd)

                buttonSalva.setOnClickListener{
                    val et = view.findViewById<EditText>(R.id.editTextAggiungiCat)
                    if("" != (et.text.toString())){
                        popupWindow.dismiss()
                        dbCat.insertData(et.text.toString())
                        Toast.makeText(this,"Categoria aggiunta", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this,"Categoria vuota", Toast.LENGTH_SHORT).show()
                    }
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(main_activity)
                }
                popupWindow.showAtLocation(main_activity, Gravity.CENTER, 0, 0)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        updateLista()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        updateLista()
    }

    private fun updateLista(){
        val dbPos = DataBasePosizioni( this)
        var datiLista = dbPos.readData()
        listaPosizioni.adapter = ListMyAdapterPosizioni(this,datiLista)
    }

}

