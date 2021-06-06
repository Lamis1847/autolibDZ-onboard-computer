package com.sil1.autolibdz_onboard_computer.data.model
import java.io.Serializable

data class Locataire(val idLocataire: Int,
                     var nom: String,
                     val prenom: String,
                     val email: String,
                     val motDePasse: String,
                     val active: Boolean,
                     val ValidationGmail : Boolean
):Serializable
