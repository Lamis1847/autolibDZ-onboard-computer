package com.sil1.autolibdz_onboard_computer.data.api

import com.sil1.autolibdz_onboard_computer.data.model.*
import retrofit2.Call
import retrofit2.http.*

interface ServiceProvider {

    @POST("api/reservation/verifyPin")
    fun codePinLogin(
        @Body info: CodePinBody
    ): Call<CodePin>

    @POST("api/trajet/createDebutTrajet")
    fun startTrajet(
        @Body info: startTrajetBody
    ): Call<startTrajetRequest>

    @GET("api/vehicules/ordi/{id}")
    fun getVehicule(@Path("id") id: Int): Call<Vehicule>

    @POST("api/pannes/signalerPanne")
    fun putPanne(@Body info: Panne) : Call<PanneResult>

    @PUT("api/vehicules/updateEtatVehicule")
    fun updateInfoVehicule(@Body vehiculeUpdate: VehiculeUpdate) : Call<String>


}