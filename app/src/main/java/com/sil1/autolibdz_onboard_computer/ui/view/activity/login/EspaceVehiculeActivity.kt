package com.sil1.autolibdz_onboard_computer.ui.view.activity.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.utils.idCar
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.activity_espace_vehicule.*

class EspaceVehiculeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_espace_vehicule)

        start()

        acceder.setOnClickListener {

            val sharedPref = this.getSharedPreferences(
                sharedPrefFile, Context.MODE_PRIVATE
            )
            with(sharedPref?.edit()) {
                this?.putInt("idVehicule", Integer.parseInt(editTextTextIdVh.text.toString()))
                idCar=Integer.parseInt(editTextTextIdVh.text.toString())
                this?.putBoolean("connected",true)
                this?.apply()
            }

            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun start() {
        val sharedPref = this.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )
        val con = sharedPref.getBoolean("connected", false)

        if(con){
            var intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
