package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mapbox.android.core.location.LocationEngineCallback
import com.mapbox.android.core.location.LocationEngineProvider
import com.mapbox.android.core.location.LocationEngineRequest
import com.mapbox.android.core.location.LocationEngineResult
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponent
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.mapbox.services.android.navigation.ui.v5.route.NavigationMapRoute
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute
import com.mapbox.turf.TurfConstants
import com.mapbox.turf.TurfMeasurement
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.model.locationFirebase
import com.sil1.autolibdz_onboard_computer.data.repositories.CodePinRepository.Companion.getDouble
import com.sil1.autolibdz_onboard_computer.data.repositories.trajetRepository
import com.sil1.autolibdz_onboard_computer.utils.*
import kotlinx.android.synthetic.main.activity_map.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Math.abs
import java.lang.ref.WeakReference
import java.text.SimpleDateFormat
import java.util.*


private lateinit var myRef: DatabaseReference
private lateinit var currentDateOfDepart: Date
private var locationFire = locationFirebase(0,0.0,0.0,0.0)
private  var latDest: Double = 36.9167 //par defaut
private  var lonDest: Double = 3.8833
class MapActivity : AppCompatActivity(), OnMapReadyCallback, PermissionsListener,
    MapboxMap.OnMapClickListener {
    private val geoJsonSourceLayerId = "GeoJsonSourceLayerId"
    private val symbolIconId = "SymbolIconId"
    private var support  =supportFragmentManager

    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var permissionsManager: PermissionsManager
    private lateinit var locationComponent: LocationComponent
    private var currentRoute: DirectionsRoute? = null
    private var navigationMapRoute: NavigationMapRoute? = null
    private val TAG = "DirectionsActivity"
    private val SOURCE_ID = "SOURCE_ID"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_map)
        var locationEngine = LocationEngineProvider.getBestLocationEngine(this)
        val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L
        val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5
        val preferences =getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        latDest = preferences.getDouble("borneFLal", 36.9167)
        lonDest = preferences.getDouble("borneFLong",3.8833)
        val depart = preferences?.getString("borneDName", "defaultValue")
        //idCar = preferences.getInt("idVehicule",0)

        val arrivee = preferences?.getString("borneFName", "defaultValue")
        departTextView.text=depart
        destinationTextView.text=arrivee

        mapView = findViewById(R.id.mapView);
        val callback = LocationListeningCallback(this,supportFragmentManager,tempsRestant,buttonstart)
        locationEngine = LocationEngineProvider.getBestLocationEngine(this)
        var request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationEngineRequest.PRIORITY_NO_POWER)
            .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME)
            .build()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        locationEngine.requestLocationUpdates(request, callback, mainLooper)
        locationEngine.getLastLocation(callback)
        var simpleDateFormat= SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val sharedPref = this.getSharedPreferences(
            sharedPrefFile, Context.MODE_PRIVATE
        )

        if (ifEndTrajet){
            buttonstart.setBackgroundColor(Color.parseColor("#FFE82E2E"))
            tempsRestant.text= "Temp estimé dépassé"
        }


        buttonstart.setOnClickListener {
            onMapClick1()
            if (!ifStartTrajet) {
                var currentDT =simpleDateFormat.format(Date())
                datebeginTrajet =currentDT
                buttonstart.setBackgroundColor(Color.parseColor("#FFFFCB00"))
                currentDT =simpleDateFormat.format(Date())
                datebeginTrajet =currentDT
                var startTrajetActivity = trajetRepository.Companion
                reservationG.etat= "Active"
                startTrajetActivity.startTrajet(
                    this, reservationG, currentDT
                )
                currentDateOfDepart= Date()

                var stop = Time(currentDateOfDepart.hours,currentDateOfDepart.minutes ,currentDateOfDepart.seconds)
                var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                var date = format.parse(datebeginTrajet)
                var start = Time(date.hours, date.minutes, date.seconds)

                timeToArrive =  difference(stop,start)
                tempsRestant.text="Temp restants "+ (timeToArrive)

            }
        }

        mapView.onCreate(savedInstanceState);
        mapView?.getMapAsync(this)
    }
    fun difference(start: Time, stop: Time): Int {
        val diff = Time(0, 0, 0)
        if (stop.minutes > start.minutes) {
            --start.hours
            start.minutes += 60
        }

        diff.minutes = start.minutes - stop.minutes
        diff.hours = start.hours - stop.hours


        return abs( diff.hours*60) + abs(diff.minutes)
    }
    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

   override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap
        mapboxMap.setStyle(getString(R.string.navigation_guidance_day)) { style: Style? ->
            if (style != null) {
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
                enableLocationComponent(style)
                addDestinationIconSymbolLayer(style);
                mapboxMap.addOnMapClickListener(this);
               /* btnStart.setOnClickListener { v: View? ->
                    val simulateRoute = true
                    val options = NavigationLauncherOptions.builder()
                        .directionsRoute(currentRoute)
                        .shouldSimulateRoute(simulateRoute)
                        .build()

                    // Call this method with Context from within an Activity
                    NavigationLauncher.startNavigation(this, options)
                }*/

            }

            setUpSource(style!!)

            setUpLayer(style!!)

            val drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_location_on_red_24dp, null)
            val bitmapUtils = BitmapUtils.getBitmapFromDrawable(drawable)
            style!!.addImage(symbolIconId, bitmapUtils!!)
        }
    }

    private fun setUpLayer(loadedMapStyle: Style) {
        loadedMapStyle.addLayer(SymbolLayer("SYMBOL_LAYER_ID", geoJsonSourceLayerId).withProperties(
            PropertyFactory.iconImage(symbolIconId),
            PropertyFactory.iconOffset(arrayOf(0f, -8f))
        ))
    }

    private fun setUpSource(loadedMapStyle: Style) {
        loadedMapStyle.addSource(GeoJsonSource(geoJsonSourceLayerId))
    }

    override fun onExplanationNeeded(permissionsToExplain: MutableList<String>?) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            mapboxMap.style?.let { enableLocationComponent(it) }
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_SHORT)
                .show();
            finish();
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onMapClick(point: LatLng): Boolean {
          /*  val destinationPoint = Point.fromLngLat(point.longitude, point.latitude)
             //val destinationPoint = Point.fromLngLat(36.7762, 3.05997)
             val originPoint = Point.fromLngLat(
                 locationComponent!!.lastKnownLocation!!.longitude,
                 locationComponent!!.lastKnownLocation!!.latitude
             )

             val source = mapboxMap!!.style!!.getSourceAs<GeoJsonSource>("destination-source-id")
             source?.setGeoJson(Feature.fromGeometry(destinationPoint))

             getRoute(originPoint, destinationPoint)
             btnStart!!.isEnabled = true
        //     btnStart!!.setBackgroundResource(R.color.mapboxBlue)
             Toast.makeText(this, destinationPoint.toString(), Toast.LENGTH_SHORT).show()
             println(destinationPoint)
*/
        return true
    }


    fun onMapClick1(): Boolean {
        // val destinationPoint = Point.fromLngLat(point.longitude, point.latitude)
        val destinationPoint = Point.fromLngLat(lonDest, latDest)
        val originPoint = Point.fromLngLat(
            locationComponent!!.lastKnownLocation!!.longitude,
            locationComponent!!.lastKnownLocation!!.latitude
        )

        val source = mapboxMap!!.style!!.getSourceAs<GeoJsonSource>("destination-source-id")
        source?.setGeoJson(Feature.fromGeometry(destinationPoint))
        //btnStart!!.isEnabled = false
        // btnStart!!.setBackgroundResource(R.color.mapboxBlue)
        getRoute(originPoint, destinationPoint)

        return true
    }

    private fun getRoute(origin: Point, destination: Point) {
        NavigationRoute.builder(this).accessToken(Mapbox.getAccessToken()!!).origin(origin)
            .destination(destination)
            .build()
            .getRoute(object : Callback<DirectionsResponse> {
                override fun onResponse(
                    call: Call<DirectionsResponse>,
                    response: Response<DirectionsResponse>
                ) {
                    // You can get the generic HTTP info about the response
                    Log.d(TAG, "Response code: " + response.body())
                    if (response.body() == null) {
                        Log.d(
                            TAG,
                            "No routes found, make sure you set the right user and access token"
                        )
                        return
                    } else if (response.body()!!.routes().size < 1) {
                        Log.e(TAG, "No routes found")
                        return
                    }
                    currentRoute = response.body()!!.routes()[0]

                    //Draw the route on the map
                    if (navigationMapRoute != null) {
                        navigationMapRoute!!.removeRoute()
                    } else {
                        navigationMapRoute = NavigationMapRoute(
                            null,
                            mapView,
                            mapboxMap!!,
                            R.style.NavigationMapRoute
                        )
                    }
                    navigationMapRoute!!.addRoute(currentRoute)
                }

                override fun onFailure(call: Call<DirectionsResponse>, t: Throwable) {
                    Log.e(TAG, "Error: " + t.message)
                }

            })
    }

    private fun addDestinationIconSymbolLayer(loadedMapStyle: Style?) {
        loadedMapStyle!!.addImage(
            "destination-icon-id", BitmapFactory.decodeResource(
                this.resources,
                R.drawable.mapbox_marker_icon_default
            )
        )

        val geoJsonSource = GeoJsonSource("destination-source-id")
        loadedMapStyle.addSource(geoJsonSource)
        val destinationSymbolLayer = SymbolLayer(
            "destination-symbol-layer-id",
            "destination-source-id"
        )
        destinationSymbolLayer.withProperties(
            PropertyFactory.iconImage("destination-icon-id"),
            PropertyFactory.iconAllowOverlap(true),
            PropertyFactory.iconIgnorePlacement(true)
        )

        loadedMapStyle.addLayer(destinationSymbolLayer)
    }

    private fun enableLocationComponent(loadedMapStyle: Style?) {
        //Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(this)) {
            // Activity the MapboxMap LocationComponent to show user location
            // Adding in LocationComponentOptions is also an optional parameter
            locationComponent = mapboxMap!!.locationComponent
            locationComponent!!.activateLocationComponent(this, loadedMapStyle!!)
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            locationComponent!!.setLocationComponentEnabled(true)

            //Set the component's camera mode
            locationComponent!!.setCameraMode(CameraMode.TRACKING)
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager!!.requestLocationPermissions(this)
        }
    }

    private class LocationListeningCallback internal constructor(
        activity: MapActivity,
        var supportFragmentManager: FragmentManager,
        var tmp: TextView,
        buttonstart: ConstraintLayout
    ) :
        LocationEngineCallback<LocationEngineResult> {

        private val activityWeakReference: WeakReference<MapActivity>
        private var runCar :Boolean=true
        private var button :ConstraintLayout=buttonstart

        init {
            this.activityWeakReference = WeakReference(activity)
        }

        override fun onSuccess(result: LocationEngineResult) {

            // The LocationEngineCallback interface's method which fires when the device's location has changed
            locationFire.id= reservationG.idVehicule
            locationFire.latitude=result.lastLocation?.latitude!!
            locationFire.longitude=result.lastLocation?.longitude!!
            locationFire.distance=TurfMeasurement.distance(Point.fromLngLat(lonDest, latDest),Point.fromLngLat(result.lastLocation?.longitude!!, result.lastLocation?.latitude!!) , TurfConstants.UNIT_KILOMETERS)

            myRef =
                FirebaseDatabase.getInstance("https://mapstest-70c5d-default-rtdb.firebaseio.com")
                    .getReference("message").child(locationFire.id.toString())
            myRef.setValue(locationFire)
            if(locationFire.distance<0.1 && runCar){
                latitude=locationFire.latitude
                longitude= locationFire.longitude
                runCar=false
                val dialog = EndTrajetFragment()
                dialog.show(supportFragmentManager, "customDialog5")

            }
            if(ifStartTrajet && !ifEndTrajet){
                currentDateOfDepart= Date()
                button.setBackgroundColor(Color.parseColor("#FFFFCB00"))

                var start = Time(currentDateOfDepart.hours,currentDateOfDepart.minutes ,currentDateOfDepart.seconds)
                var format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                var date = format.parse(datebeginTrajet)
                var stop = Time(date.hours, date.minutes, date.seconds)
                val diff = Time(0, 0, 0)
                if (stop.minutes > start.minutes) {
                    --start.hours
                    start.minutes += 60
                }
                 println("start "+start.hours+"start minute"+start.minutes)
                println("end "+stop.hours+" end minute"+stop.minutes)

                diff.minutes = start.minutes - stop.minutes
                diff.hours = start.hours - stop.hours
                var rest=reservationG.tempsEstime-(abs( diff.hours*60) + abs(diff.minutes))
                if(rest>0){
                    tmp.text= "temp restant "+ rest+" min"
                }else{

                    button.setBackgroundColor(Color.parseColor("#FFE82E2E"))
                    tmp.text= "Temp estimé dépassé"
                    ifEndTrajet=true

                }
                println(abs( diff.hours*60) + abs(diff.minutes))
            }
            if(ifEndTrajet){
                button.setBackgroundColor(Color.parseColor("#FFE82E2E"))
                tmp.text= "Temp estimé dépassé"
            }
            result.getLastLocation()
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location can not be captured
         *
         * @param exception the exception message
         */
        override fun onFailure(exception: Exception) {

            // The LocationEngineCallback interface's method which fires when the device's location can not be captured


        }
    }
}

class Time(internal var hours: Int, internal var minutes: Int, internal var seconds: Int)
