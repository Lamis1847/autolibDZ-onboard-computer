package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne.ReportPanneActivity
import com.sil1.autolibdz_onboard_computer.ui.view.activity.suivi_etat.SuiviActivity

import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_menu_bar.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val preferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val nom = preferences.getString("nom_loc", "User")

        locataireName.text= "Bonjour "+nom

        appButton.setOnClickListener {
            val myIntent = Intent(this, SuiviActivity::class.java)
            startActivity(myIntent)
        }

        triangleAlertButton.setOnClickListener {
            val myIntent = Intent(this, ReportPanneActivity::class.java)
            startActivity(myIntent)
        }

        appButton.setOnClickListener {
            val myIntent = Intent(this, SuiviActivity::class.java)
            startActivity(myIntent)
        }

    }

}