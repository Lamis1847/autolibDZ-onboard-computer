package com.sil1.autolibdz_onboard_computer.data.api

import com.sil1.autolibdz_onboard_computer.data.model.CodePin
import com.sil1.autolibdz_onboard_computer.data.model.CodePinBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServiceProvider {

    @POST("api/reservation/verifyPin")
    fun codePinLogin(
        @Body info: CodePinBody
    ): Call<CodePin>
}