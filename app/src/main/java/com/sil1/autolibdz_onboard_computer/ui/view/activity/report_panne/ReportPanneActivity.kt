package com.sil1.autolibdz_onboard_computer.ui.view.activity.report_panne

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.ui.view.activity.suivi_etat.SuiviActivity
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.activity_report_panne.*
import kotlinx.android.synthetic.main.fragment_menu_bar.*
import java.io.IOException
import java.util.*

class ReportPanneActivity : AppCompatActivity() {
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_panne)

        constraintLayout1.setOnClickListener {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
                    val location = task.result
                    if (location != null) {
                        try {
                            var geocoder = Geocoder(this, Locale.getDefault())
                            var adress = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                            var latitude = adress.get(0).latitude.toFloat()
                            var longitude = adress.get(0).longitude.toFloat()

                            val sharedPref = this.getSharedPreferences(
                                sharedPrefFile, Context.MODE_PRIVATE
                            )
                            with(sharedPref?.edit()) {
                                this?.putFloat("panneLal", latitude)
                                this?.putFloat("panneLong", longitude)
                                this?.apply()
                            }
                            val dialog = DescriptionFragment()
                            dialog.show(supportFragmentManager, "customDialog4")

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        constraintLayout11.setOnClickListener {

            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
                    val location = task.result
                    if (location != null) {
                        try {
                            val geocoder = Geocoder(this, Locale.getDefault())
                            val adress = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                            val latitude = adress.get(0).latitude.toFloat()
                            val longitude = adress.get(0).longitude.toFloat()

                            val sharedPref = this.getSharedPreferences(
                                sharedPrefFile, Context.MODE_PRIVATE
                            )
                            with(sharedPref?.edit()) {
                                this?.putFloat("panneLal", latitude)
                                this?.putFloat("panneLong", longitude)
                                this?.apply()
                            }
                            val dialog = PanneMoteurFragment()
                            dialog.show(supportFragmentManager, "customDialog1")

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        constraintLayout13.setOnClickListener {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
                    val location = task.result
                    if (location != null) {
                        try {
                            val geocoder = Geocoder(this, Locale.getDefault())
                            val adress = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                            val latitude = adress.get(0).latitude.toFloat()
                            val longitude = adress.get(0).longitude.toFloat()

                            val sharedPref = this.getSharedPreferences(
                                sharedPrefFile, Context.MODE_PRIVATE
                            )
                            with(sharedPref?.edit()) {
                                this?.putFloat("panneLal", latitude)
                                this?.putFloat("panneLong", longitude)
                                this?.apply()
                            }
                            val dialog = PanneExtFragment()
                            dialog.show(supportFragmentManager, "customDialog2")

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        constraintLayout14.setOnClickListener {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationClient.lastLocation.addOnCompleteListener { task: Task<Location> ->
                    val location = task.result
                    if (location != null) {
                        try {
                            val geocoder = Geocoder(this, Locale.getDefault())
                            val adress = geocoder.getFromLocation(location.latitude, location.longitude, 1)

                            val latitude = adress.get(0).latitude.toFloat()
                            val longitude = adress.get(0).longitude.toFloat()

                            val sharedPref = this.getSharedPreferences(
                                sharedPrefFile, Context.MODE_PRIVATE
                            )
                            with(sharedPref?.edit()) {
                                this?.putFloat("panneLal", latitude)
                                this?.putFloat("panneLong", longitude)
                                this?.apply()
                            }
                            val dialog = PanneIntFragment()
                            dialog.show(supportFragmentManager, "customDialog3")

                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }

        //aller vers le menu HOME
        homeButton.setOnClickListener {
            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        //suivre état du vehicule
        appButton.setOnClickListener {
            val myIntent = Intent(this, SuiviActivity::class.java)
            startActivity(myIntent)
        }

    }
}