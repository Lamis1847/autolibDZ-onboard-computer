package com.sil1.autolibdz_onboard_computer.data.repositories

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Parcelable
import android.widget.Toast
import com.google.gson.Gson
import com.sil1.autolibdz_onboard_computer.data.api.ServiceBuilder
import com.sil1.autolibdz_onboard_computer.data.api.ServiceProvider
import com.sil1.autolibdz_onboard_computer.data.model.CodePin
import com.sil1.autolibdz_onboard_computer.data.model.CodePinBody
import com.sil1.autolibdz_onboard_computer.data.model.Reservation
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CodePinRepository {
    lateinit var resv: Reservation

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }

        @SuppressLint("RestrictedApi")
        fun codePin(
            context: Context,
            code: String
        ) {
            val sharedPref = context.getSharedPreferences(
                sharedPrefFile, Context.MODE_PRIVATE
            )
            val id = sharedPref.getInt("idVehicule", 0)
            var loginBody = CodePinBody(id,code)

            val loginRequest = api.codePinLogin(loginBody)

            loginRequest.enqueue(object : Callback<CodePin> {

                @SuppressLint("RestrictedApi")
                override fun onResponse(call: Call<CodePin>, response: Response<CodePin>) {

                    if (!response.isSuccessful()) {
                        val gson = Gson()
                        val message: CodePin = gson.fromJson(
                            response.errorBody()!!.charStream(),
                            CodePin::class.java
                        )
                        println(response.errorBody())
                        Toast.makeText(context, "CODE PIN éronné", Toast.LENGTH_LONG).show()


                    } else {
                        val resp = response.body()
                        if (resp != null) {
                            with(sharedPref?.edit()) {
                                reservationG = resp.reservation
                                this?.putString("nom_loc", resp.locataire.nom)
                                this?.putString("borneDName", resp.bornDepart.nomBorne)
                                this?.putString("borneFName", resp.bornDestination.nomBorne)
                                this?.putDouble("borneDLong", resp.bornDepart.longitude)
                                this?.putDouble("borneDLal", resp.bornDepart.latitude)
                                this?.putDouble("borneFLal", resp.bornDestination.latitude)
                                this?.putDouble("borneFLong", resp.bornDestination.longitude)
                                this?.putDouble("borneFLong", resp.bornDestination.longitude)
                                this?.putInt("tempsRestant", resp.reservation.tempsEstime)
                                this?.putDouble("prix estime", resp.reservation.prixEstime)

                                this?.apply()
                            }
                        }
                        Toast.makeText(context, "Connexion établie", Toast.LENGTH_SHORT).show()
                        val myIntent = Intent(context, MainActivity::class.java)

                        context.startActivity(myIntent)
                        (context as Activity).finish()

                    }
                }
                override fun onFailure(call: Call<CodePin>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }

        inline fun SharedPreferences.Editor.putDouble(
            key: String,
            value: Double
        ): SharedPreferences.Editor {
            putLong(key, value.toRawBits())
            return this
        }

        fun SharedPreferences.getDouble(key: String, defValue: Double) =
            Double.fromBits(getLong(key, defValue.toRawBits()))
    }
}



