package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sil1.autolibdz_onboard_computer.R
import kotlinx.android.synthetic.main.fragment_popup_panne.*
import kotlinx.android.synthetic.main.fragment_popup_panne.view.*
import kotlinx.android.synthetic.main.fragment_report_panne_one.*


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

                    Toast.makeText(
                        applicationContext,
                        resources.getStringArray(R.array.panne_moteur)[position],
                        Toast.LENGTH_SHORT
                    )
                        .show()
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

    }

}