package com.sil1.autolibdz_onboard_computer.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.sil1.autolibdz_onboard_computer.data.api.ServiceBuilder
import com.sil1.autolibdz_onboard_computer.data.api.ServiceProvider
import com.sil1.autolibdz_onboard_computer.data.model.Vehicule
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SuiviRepository {
    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }


        @SuppressLint("RestrictedApi")
        fun vehicule(
            context: Context,
            id: Int
        ) {
            val call = api.getVehicule(id)
            val sharedPref = context.getSharedPreferences(
                sharedPrefFile, Context.MODE_PRIVATE
            )

            call.enqueue(object : Callback<Vehicule> {

                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<Vehicule>, response: Response<Vehicule>) {
                    if (!response.isSuccessful()) {
                        val gson = Gson()
                        val message: Vehicule = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            Vehicule::class.java
                        )
                    } else {
                        val resp = response.body()

                        if (resp != null) {
                            with(sharedPref?.edit()) {
                                this?.putInt("limiteurVitesse", resp.limiteurVitesse)
                                this?.putInt("pressionPneus", resp.pressionPneus)
                                this?.putInt("chargeBatterie", resp.chargeBatterie)
                                this?.putInt("niveauMinimumHuile", resp.niveauMinimumHuile)
                                this?.putInt("tempsDeRefroidissement", resp.tempsDeRefroidissement)
                                this?.putInt("pressionHuileMoteur", resp.pressionHuileMoteur)
                                this?.putString("etat", resp.etat)
                                this?.putString("anomalieCircuit", resp.anomalieCircuit)
                                this?.putInt("regulateurVitesse", resp.regulateurVitesse)
                                this?.apply()
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<Vehicule>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}