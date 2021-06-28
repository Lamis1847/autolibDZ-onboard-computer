package com.sil1.autolibdz_onboard_computer.ui.view.activity.suivi_etat

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.SuiviRepository
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne.ReportPanneActivity
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_menu_bar.*
import kotlinx.android.synthetic.main.suivi_etat.*
import java.util.*
import kotlin.concurrent.schedule


class SuiviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.suivi_etat)

        val preferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        var vehiculeGet = SuiviRepository.Companion
        val id = preferences.getInt("idVehicule", 0)
        vehiculeGet.vehicule(this.applicationContext, id)


        var limitVit = preferences?.getInt("limiteurVitesse", 0)
        var pressionPneu = preferences?.getInt("pressionPneus", 0)
        var chargeBatt = preferences?.getInt("chargeBatterie", 0)
        var niveauHuile = preferences?.getInt("niveauMinimumHuile", 0)
        val tempsRef = preferences?.getInt("tempsDeRefroidissement", 0)
        val pressionHuile= preferences?.getInt("pressionHuileMoteur", 0)
        val etat = preferences?.getString("etat", "null")
        val anomalie = preferences?.getString("anomalieCircuit", "null")
        val regVit = preferences?.getInt("regulateurVitesse", 0)


        if (etat=="en service") {
            constraintLayout5.setBackgroundColor(Color.GREEN)
            textViewRealState.text = etat
            textView23.text =limitVit.toString()+" KM/H"
            textView18.text = pressionPneu.toString()+" Bar"
            textView17.text = chargeBatt.toString()+" %"
            textView22.text = tempsRef.toString()+" s"
            textView21.text = niveauHuile.toString()+" litres"
            textView20.text = pressionHuile.toString()+" Bar"
            textView24.text = anomalie
            tempRealText.text = regVit.toString()+" KM/H"


            /*marche bien
            Handler().postDelayed({


                var vehiculeGet = SuiviRepository.Companion
                val id = preferences.getInt("idVehicule", 0)
                vehiculeGet.vehicule(this.applicationContext, id)


                var pressionPneu = preferences?.getInt("pressionPneus", 0)
                var s1 = soustract(pressionPneu!!)
                textView18.text = s1+" Bar"
                //ajouter a la base de données la valeur s qui est modifiée

                var chargeBatt = preferences?.getInt("chargeBatterie", 0)
                var s2 = soustract(chargeBatt!!)
                textView17.text = s2+" %"

                var niveauHuile = preferences?.getInt("niveauMinimumHuile", 0)
                var s3 = soustract(niveauHuile!!)
                textView21.text = s3+" litres"

                val pressionHuile= preferences?.getInt("pressionHuileMoteur", 0)
                var s4 = soustract(pressionHuile!!)
                textView20.text = s4+" Bar"
                }, 10000)*/


        } else {
            constraintLayout5.setBackgroundColor(Color.RED)
            textViewRealState.text = etat
            textView23.text =limitVit.toString()+" KM/H"
            textView18.text = pressionPneu.toString()+" Bar"
            textView17.text = chargeBatt.toString()+" %"
            textView22.text = tempsRef.toString()+" s"
            textView21.text = niveauHuile.toString()+" litres"
            textView20.text = pressionHuile.toString()+" Bar"
            textView24.text = anomalie
            tempRealText.text = regVit.toString()+" KM/H"
        }

        homeButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        triangleAlertButton.setOnClickListener {
            val myIntent = Intent(this, ReportPanneActivity::class.java)
            startActivity(myIntent)
        }
    }

    /*fun soustract1(a : String, b : String, c : String) {
            textView23.text =(a.toInt()-1).toString()+" Bar"
            textView18.text = (b.toInt()-1).toString()+" %"
            textView17.text = (c.toInt()-1).toString()+" Bar"


        /*if (a.toInt()!= 0) {
            Handler().postDelayed({
            soustract((a.toInt()-1).toString(), (b.toInt()-1).toString(),
                (c.toInt()-1).toString(), (d.toInt()-1).toString(), (e.toInt()-1).toString(), (f.toInt()-1).toString(),
                (g.toInt()-1).toString())
            }, 5000)
        }*/
    }*/

    fun soustract(a : Int) : String {
        val s = a-1
        return s.toString()
    }
}