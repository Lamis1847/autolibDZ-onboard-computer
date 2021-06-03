package com.sil1.autolibdz_onboard_computer.data.model

data class Locataire(val idLocataire: Int,
                     var nom: String,
                     val prenom: String,
                     val email: String,
                     val motDePasse: String,
                     val active: Boolean,
                     val ValidationGmail : Boolean
)
