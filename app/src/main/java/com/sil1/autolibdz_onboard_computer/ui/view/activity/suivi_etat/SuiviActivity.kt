package com.sil1.autolibdz_onboard_computer.ui.view.activity.suivi_etat

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.SuiviRepository
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne.ReportPanneActivity
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_menu_bar.*
import kotlinx.android.synthetic.main.fragment_suivi_one.*


class SuiviActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_suivi_one)

        var vehiculeGet = SuiviRepository.Companion
        vehiculeGet.vehicule(this.applicationContext, 123333)

        val preferences = this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val limitVit = preferences?.getInt("limiteurVitesse", 0)
        val pressionPneu = preferences?.getInt("pressionPneus", 0)
        val chargeBatt = preferences?.getInt("chargeBatterie", 0)
        val niveauHuile = preferences?.getInt("niveauMinimumHuile", 0)
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

        /*fragment.homeButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        fragment.triangleAlertButton.setOnClickListener {
            val myIntent = Intent(this, ReportPanneActivity::class.java)
            startActivity(myIntent)
        }*/
    }
}