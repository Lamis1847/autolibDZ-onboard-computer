package com.sil1.autolibdz_onboard_computer.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Toast
import com.google.gson.Gson
import com.sil1.autolibdz_onboard_computer.data.api.ServiceBuilder
import com.sil1.autolibdz_onboard_computer.data.api.ServiceProvider
import com.sil1.autolibdz_onboard_computer.data.model.*
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PanneRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }


        @SuppressLint("RestrictedApi")
        fun putPanne(
            context: Context,
            idVehicule: Int,
            description: String,
            longitude: Float?,
            latitude: Float?
        ) {

            val panne = Panne(idVehicule, description, longitude,latitude)

            println(panne)

            val panneRequest = api.putPanne(panne)

            panneRequest.enqueue(object : Callback<PanneResult> {

                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<PanneResult>, response: Response<PanneResult>) {

                    if (!response.isSuccessful()) {
                        val gson = Gson()
                        val message: PanneResult = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            PanneResult::class.java
                        )
                        println(response.errorBody())
                        Toast.makeText(context, "Veuillez ré-essayer", Toast.LENGTH_LONG).show()


                    } else {

                        Toast.makeText(
                            context,
                            "Votre réclamation a bien été enregistrée",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<PanneResult>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}