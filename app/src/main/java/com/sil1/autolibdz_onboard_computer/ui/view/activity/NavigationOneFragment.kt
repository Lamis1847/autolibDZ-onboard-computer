package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sil1.autolibdz_onboard_computer.R
import kotlinx.android.synthetic.main.fragment_navigation_one.*


@Suppress("UNREACHABLE_CODE")
class NavigationOneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_one, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        constraintLayoutDemarrer.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_navigationOneFragment_to_navigationTwoFragment)
        }
    }

}