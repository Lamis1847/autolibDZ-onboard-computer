package com.sil1.autolibdz_onboard_computer.data.model

data class CodePin(var success : Boolean,
                   var reservation : Reservation,
                   var borneDepart : Borne,
                   var borneDestination : Borne,
                   var locataire : Locataire
)
