package com.sil1.autolibdz_onboard_computer.data.api

import com.sil1.autolibdz_onboard_computer.data.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ServiceProvider {

    @POST("api/reservation/verifyPin")
    fun codePinLogin(
        @Body info: CodePinBody
    ): Call<CodePin>

    @POST("api/trajet/createDebutTrajet")
    fun startTrajet(
        @Body info: startTrajetBody
    ): Call<startTrajetRequest>

    @GET("api/vehicules/{id}")
    fun getVehicule(@Path("id") id: Int): Call<Vehicule>


    @POST("api/pannes/signalerPanne")
    fun putPanne(@Body info: Panne) : Call<PanneResult>

    //HyperTrack
    @POST("api/track/start/{id}")
    fun startTrack(@Path("id") id: Int) : Call<String>

    @POST("api/track/stop/{id}")
    fun stopTrack(@Path("id") id: Int) : Call<String>

    @GET("api/vehicules/{id}")
    fun getPositionTrack(@Path("id") id: Int): Call<PanneResult>


}