package com.sil1.autolibdz_onboard_computer.data.model

data class CodePin(var success : Boolean,
                   var idReservation: Int, var etat: String, var idVehicule: Int,
                   var idLocataire: Int, var idBorneDepart: String,
                   var idBorneDestination : String, var codePin: String)
