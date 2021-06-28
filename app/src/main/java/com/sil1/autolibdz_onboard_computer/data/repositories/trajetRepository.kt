package com.sil1.autolibdz_onboard_computer.data.repositories

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.gson.Gson
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.api.ServiceBuilder
import com.sil1.autolibdz_onboard_computer.data.api.ServiceProvider
import com.sil1.autolibdz_onboard_computer.data.model.*
import com.sil1.autolibdz_onboard_computer.data.repositories.CodePinRepository.Companion.putDouble
import com.sil1.autolibdz_onboard_computer.ui.view.activity.HomeStateOnDriveFragment
import com.sil1.autolibdz_onboard_computer.utils.ifStartTrajet
import com.sil1.autolibdz_onboard_computer.utils.reservationG
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class trajetRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }
        fun startTrajet(
            context: Context,
            info: Reservation,
            dateDebut: String

        ) {
            var startTrajetbody = startTrajetBody(info, dateDebut)
            val sharedPref = context.getSharedPreferences(
                sharedPrefFile, Context.MODE_PRIVATE
            )
            val startTrajetRequest = api.startTrajet(startTrajetbody)
            startTrajetRequest.enqueue(object : Callback<startTrajetRequest> {

                override fun onResponse(
                    call: Call<startTrajetRequest>,
                    response: Response<startTrajetRequest>
                ) {
                    if (!response.isSuccessful()) {
                        val gson = Gson()

                        println(response.errorBody())
                        Toast.makeText(context, "un problem est produit", Toast.LENGTH_LONG).show()
                        val resp = response.body()


                    } else {
                        val resp = response.body()
                        if (resp != null) {
                            with(sharedPref?.edit()) {
                                this?.putInt("idTrajet", resp.idTrajet)
                                this?.putString("dateBeginTrajet",dateDebut)
                                this?.apply()
                            }
                        }
                        ifStartTrajet =true
                        println("begin your trajet at from trajet repository"+dateDebut)
                        Toast.makeText(context, "Commencer trajet", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<startTrajetRequest>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                    println(t.message)
                }
            })
        }

        fun finTrajet(
            view: View?,
            context: Context,
            endTrajetBody: finTrajetBody

        ) {

            val finTrajetRequest = api.finTrajet(endTrajetBody)
            finTrajetRequest.enqueue(object : Callback<finTrajetRequest> {

                override fun onResponse(
                    call: Call<finTrajetRequest>,
                    response: Response<finTrajetRequest>
                ) {
                    if (!response.isSuccessful()) {
                        val gson = Gson()
                        println(response.errorBody())
                        Toast.makeText(context, "un problem est produit", Toast.LENGTH_LONG).show()
                        val resp = response.body()


                    } else {
                        val resp = response.body()

                        Toast.makeText(context, "Fin de votre trajet", Toast.LENGTH_SHORT).show()

                    }

                }

                override fun onFailure(call: Call<finTrajetRequest>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                    println(t.message)
                }
            })
        }
    }
}