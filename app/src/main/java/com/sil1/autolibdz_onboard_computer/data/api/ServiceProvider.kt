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
}