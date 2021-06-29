package com.sil1.autolibdz_onboard_computer.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import com.sil1.autolibdz_onboard_computer.data.api.ServiceBuilder
import com.sil1.autolibdz_onboard_computer.data.api.ServiceProvider
import com.sil1.autolibdz_onboard_computer.data.model.CodePin
import com.sil1.autolibdz_onboard_computer.data.model.CodePinBody
import com.sil1.autolibdz_onboard_computer.data.model.VehiculeUpdate
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.utils.reservationG
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateVehiculeRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        @SuppressLint("RestrictedApi")
        fun update(
            context: Context,
            id : Int,
            pressionHuileMoteur: Int,
            chargeBatterie : Int,
            pressionPneus : Int
        ) {

            var vehiculeUpdate = VehiculeUpdate(id, pressionHuileMoteur,
            chargeBatterie,
            pressionPneus)

            val stateVehicule = api.updateInfoVehicule(vehiculeUpdate)

            stateVehicule.enqueue(object : Callback<String> {

                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<String>, response: Response<String>) {

                    if (!response.isSuccessful()) {
                        println(response.errorBody())
                        Toast.makeText(context, "Aucun objet", Toast.LENGTH_LONG).show()
                    } else {
                        println("***********************************")
                        println("UPDATED")
                        println("***********************************")
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable) {
                }
            })
        }
    }
}