package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.CodePinRepository.Companion.getDouble
import com.sil1.autolibdz_onboard_computer.utils.*
import kotlinx.android.synthetic.main.fragment_navigation_one.*

class MapsFragment : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val preferences = this.activity?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val borneDLal = preferences?.getDouble("borneDLal",0.0)
        val markerD =  preferences?.getString("borneDName","defalut")
        val borneDLong = preferences?.getDouble("borneDLong",0.0)
        val dep = LatLng(borneDLal!!, borneDLong!!)
        googleMap.addMarker(MarkerOptions().position(dep).title(markerD))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(dep))


        val borneFLal = preferences?.getDouble("borneFLal",0.0)
        val borneFLong = preferences?.getDouble("borneFLong",0.0)
        val markerF =  preferences?.getString("borneFName","defalut")
        val dest = LatLng(borneFLal!!, borneFLong!!)
        googleMap.addMarker(MarkerOptions().position(dest).title(markerF))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(dest))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

}
