package com.sil1.autolibdz_onboard_computer.utils

import com.sil1.autolibdz_onboard_computer.data.model.Reservation
import java.util.*

val sharedPrefFile: String = "kotlinsharedpreference"
var ifStartTrajet: Boolean=false
var timeToArrive: Int=0
var datebeginTrajet:String=""
var reservationG:Reservation = Reservation(
    0, "Active", 0, 0, 0, 0, "", 0,
    0.0, 0.0
)

class utils {

}