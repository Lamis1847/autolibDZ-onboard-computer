package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sil1.autolibdz_onboard_computer.R
import kotlinx.android.synthetic.main.activity_report_panne.*

class ReportPanneActivity : AppCompatActivity() {

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_panne)

        //lateinit var fusedLocationClient: FusedLocationProviderClient

        constraintLayout1.setOnClickListener {

            /*fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location!=null) {
                        var latitude = location?.latitude.toFloat()
                        var longitude = location?.longitude.toFloat()
                        val sharedPref = this.getSharedPreferences(
                            sharedPrefFile, Context.MODE_PRIVATE
                        )
                        with(sharedPref?.edit()) {
                            this?.putFloat("panneLal", latitude)
                            this?.putFloat("panneLong", longitude)
                            this?.apply()
                        }
                    }
                }*/

            var dialog = DescriptionFragment()
            dialog.show(supportFragmentManager, "customDialog4")
        }

        constraintLayout11.setOnClickListener {

            var dialog = PanneMoteurFragment()
            dialog.show(supportFragmentManager, "customDialog1")

        }


        constraintLayout13.setOnClickListener {

            var dialog = PanneExtFragment()
            dialog.show(supportFragmentManager, "customDialog2")
        }

        constraintLayout14.setOnClickListener {

            var dialog = PanneIntFragment()
            dialog.show(supportFragmentManager, "customDialog3")
        }

    }
}