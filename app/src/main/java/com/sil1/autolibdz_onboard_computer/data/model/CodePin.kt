package com.sil1.autolibdz_onboard_computer.data.model

import java.io.Serializable

data class CodePin (var success : Boolean,
                   var reservation : Reservation,
                   var bornDepart : Borne,
                   var bornDestination : Borne,
                   var locataire : Locataire
)
