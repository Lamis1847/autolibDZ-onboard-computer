package com.sil1.autolibdz_onboard_computer.data.model

data class Reservation(val idReservation: Int,
                       var etat: String,
                        val idVehicule: Int,
                        val idLocataire: Int,
                        val idBorneDepart: Int,
                        val idBorneDestination: Int,
                        val codePin: String,
                        val tempsEstime: Int,
                        val distanceEstime: Double,
                        val prixEstime: Double
                        )
