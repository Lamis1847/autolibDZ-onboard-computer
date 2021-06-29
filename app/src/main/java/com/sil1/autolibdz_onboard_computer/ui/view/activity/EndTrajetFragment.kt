package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.model.finTrajetBody
import com.sil1.autolibdz_onboard_computer.data.repositories.PanneRepository
import com.sil1.autolibdz_onboard_computer.data.repositories.trajetRepository
import com.sil1.autolibdz_onboard_computer.utils.*
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_description.view.*
import kotlinx.android.synthetic.main.fragment_end_trajet.view.*
import java.text.SimpleDateFormat
import java.util.*


class EndTrajetFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View = inflater.inflate(R.layout.fragment_end_trajet, container, false)
        rootView.endTrajetButton.setOnClickListener {
            var endTrajetActivity = trajetRepository.Companion
            var simpleDateFormat= SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            var currentDT =simpleDateFormat.format(Date())

            var endtrajet = finTrajetBody(latitude, longitude,reservationG.idVehicule,currentDT,reservationG.prixEstime,idTrajet,datebeginTrajet,reservationG.tempsEstime)
            endTrajetActivity.finTrajet(
                requireContext(), endtrajet
            )
            dismiss()
        }
        return rootView
    }

}