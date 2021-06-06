package com.sil1.autolibdz_onboard_computer.data.api

import com.sil1.autolibdz_onboard_computer.data.model.CodePin
import com.sil1.autolibdz_onboard_computer.data.model.CodePinBody
import com.sil1.autolibdz_onboard_computer.data.model.startTrajetBody
import com.sil1.autolibdz_onboard_computer.data.model.startTrajetRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceProvider {

    @POST("api/reservation/verifyPin")
    fun codePinLogin(
        @Body info: CodePinBody
    ): Call<CodePin>

    @POST("api/trajet/createDebutTrajet")
    fun startTrajet(
        @Body info: startTrajetBody
    ): Call<startTrajetRequest>
}