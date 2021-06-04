package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import kotlinx.android.synthetic.main.fragment_navigation_one.*
import kotlinx.android.synthetic.main.fragment_navigation_one.destinationTextView
import kotlinx.android.synthetic.main.fragment_navigation_two.*
import kotlinx.android.synthetic.main.fragment_navigation_two.retour

class NavigationTwoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_two, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val destination = preferences?.getString("borneFName", "defaultValue")

        destinationTextView.text = destination

        retour.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_navigationTwoFragment_to_navigationOneFragment)
        }

    }


}