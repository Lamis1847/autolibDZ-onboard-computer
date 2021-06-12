package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.ui.view.activity.suivi_etat.SuiviActivity
import kotlinx.android.synthetic.main.fragment_menu_bar.*
import kotlinx.android.synthetic.main.fragment_popup_panne.*
import kotlinx.android.synthetic.main.fragment_popup_panne.view.*
import kotlinx.android.synthetic.main.fragment_report_panne_one.*
import kotlinx.android.synthetic.main.fragment_report_panne_one.fragment
import kotlinx.android.synthetic.main.fragment_suivi_one.*


class ReportPanneActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_panne)

        constraintLayout1.setOnClickListener {
            val alert = CustomAlert()
            alert.showDialog(this)
        }

        constraintLayout11.setOnClickListener {

            val view = LayoutInflater.from(applicationContext).inflate(
                R.layout.fragment_popup_panne,
                null
            )

            view.spinner01.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    val selectedOption = view?.spinner01?.selectedItem.toString()
                    println(selectedOption)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }

            val alert = CustomAlert()
            alert.showDialog2(this)
        }
        /*constraintLayout11.setOnClickListener {
            val alert = CustomAlert()
            alert.showDialog2(this)
        }*/

        /*fragment.homeButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        fragment.appButton.setOnClickListener {
            val myIntent = Intent(this, SuiviActivity::class.java)
            startActivity(myIntent)
        }*/

    }

}