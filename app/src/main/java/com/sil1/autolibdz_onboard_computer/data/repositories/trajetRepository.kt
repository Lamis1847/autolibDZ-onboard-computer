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
import com.sil1.autolibdz_onboard_computer.ui.view.activity.HomeStateOnDriveFragment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class trajetRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }


        fun startTrajet(
            view: View?,
            context: Context,
            info: Reservation,
            dateDebut: String

        ) {
            var startTrajetbody = startTrajetBody(info, dateDebut)

            val startTrajetRequest = api.startTrajet(startTrajetbody)
            startTrajetRequest.enqueue(object : Callback<startTrajetRequest> {

                override fun onResponse(
                    call: Call<startTrajetRequest>,
                    response: Response<startTrajetRequest>
                ) {
                    if (!response.isSuccessful()) {
                        val gson = Gson()

                        println(response.errorBody())
                        Toast.makeText(context, "problem", Toast.LENGTH_LONG).show()
                        val resp = response.body()


                    } else {
                        val resp = response.body()

                        Toast.makeText(context, "have fun", Toast.LENGTH_SHORT).show()
                        view?.findNavController()?.navigate(R.id.action_homeFragment_to_homeStateOnDriveFragment)

                    }

                }

                override fun onFailure(call: Call<startTrajetRequest>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                    println(t.message)
                }
            })
        }

    }
}