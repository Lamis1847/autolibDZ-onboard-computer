package com.sil1.autolibdz_onboard_computer.data.model

data class Borne(val idBorne: Int,
                 val nomBorne: String,
                 val wilaya: String,
                 val commune: String,
                 val latitude: Double,
                 val longitude: Double,
                 val nbVehicules: Int,
                 val nbPlaces: Int,
                 val etat: Int
)
