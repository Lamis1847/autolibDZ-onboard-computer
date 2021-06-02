package com.sil1.autolibdz_onboard_computer.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.google.gson.Gson
import com.sil1.autolibdz_onboard_computer.data.api.ServiceBuilder
import com.sil1.autolibdz_onboard_computer.data.api.ServiceProvider
import com.sil1.autolibdz_onboard_computer.data.model.CodePin
import com.sil1.autolibdz_onboard_computer.data.model.CodePinBody
import com.sil1.autolibdz_onboard_computer.ui.view.activity.MainActivity
import com.sil1.autolibdz_onboard_computer.utils.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CodePinRepository  {

    companion object {

        val api: ServiceProvider by lazy {
            ServiceBuilder.buildService(ServiceProvider::class.java)
        }


        fun codePin(
            context: Context,
            code: String
        ) {

            var loginBody = CodePinBody(456749, code)
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
                        Toast.makeText(context, "CODE PIN éronné", Toast.LENGTH_LONG).show()
                        val resp = response.body()


                    } else {
                        val resp = response.body()
                        if(resp!=null){ //shared prefernces ?
                            locName = resp.locataire.nom
                            /*borneDLong = resp.borneDepart.longitude
                            borneDLal = resp.borneDepart.latitude
                            borneFLong = resp.borneDestination.longitude
                            borneFLal = resp.borneDestination.latitude
                            borneDName = resp.borneDepart.nomBorne
                            borneFName = resp.borneDestination.nomBorne
                            tempsRestant = resp.reservation.tempsEstime*/
                        }
                        Toast.makeText(context, "Connexion établie", Toast.LENGTH_SHORT).show()
                        val myIntent = Intent(context, NavigationActivity::class.java)
                        context.startActivity(myIntent)
                    }
                }

                override fun onFailure(call: Call<CodePin>, t: Throwable) {
                    Toast.makeText(context, "Erreur", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}