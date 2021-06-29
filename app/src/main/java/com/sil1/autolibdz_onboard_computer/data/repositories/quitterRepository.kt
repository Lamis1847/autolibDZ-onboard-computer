package com.sil1.autolibdz_onboard_computer.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.sil1.autolibdz_onboard_computer.data.api.ServiceBuilder
import com.sil1.autolibdz_onboard_computer.data.api.ServiceProvider
import com.sil1.autolibdz_onboard_computer.data.model.Quitter
import com.sil1.autolibdz_onboard_computer.data.model.QuitterReservation
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class quitterRepository {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }


        @SuppressLint("RestrictedApi")
        fun quite(
            context: Context,
            id: Int
        ) {
            val q = QuitterReservation(id)
            val call = api.quitter(q)

            call.enqueue(object : Callback<Quitter> {

                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<Quitter>, response: Response<Quitter>) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Aucun objet", Toast.LENGTH_LONG).show()
                    } else {
                        println("***********************************")
                        println("UPDATED")
                        println("***********************************")
                    }
                }

                override fun onFailure(call: Call<Quitter>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}