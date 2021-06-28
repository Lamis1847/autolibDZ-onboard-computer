package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.PanneRepository
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_description.view.*
import kotlinx.android.synthetic.main.fragment_end_trajet.view.*


class EndTrajetFragment : DialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var rootView: View = inflater.inflate(R.layout.fragment_end_trajet, container, false)
        rootView.endTrajetButton.setOnClickListener {
            dismiss()
        }
        return rootView
    }

}