package com.sil1.autolibdz_onboard_computer.ui.view.activity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.api.directions.v5.DirectionsCriteria
import com.mapbox.api.directions.v5.MapboxDirections
import com.mapbox.api.directions.v5.models.DirectionsResponse
import com.mapbox.api.directions.v5.models.DirectionsRoute
import com.mapbox.core.constants.Constants.PRECISION_6
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.LineString
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.maps.Style.OnStyleLoaded
import com.mapbox.mapboxsdk.style.layers.LineLayer
import com.mapbox.mapboxsdk.style.layers.Property
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.*
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.sil1.autolibdz_onboard_computer.R
import com.sil1.autolibdz_onboard_computer.data.repositories.CodePinRepository.Companion.getDouble
import com.sil1.autolibdz_onboard_computer.utils.sharedPrefFile
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TryActivity : AppCompatActivity() {

    private val ROUTE_LAYER_ID = "route-layer-id"
    private val ROUTE_SOURCE_ID = "route-source-id"
    private val ICON_LAYER_ID = "icon-layer-id"
    private val ICON_SOURCE_ID = "icon-source-id"
    private val RED_PIN_ICON_ID = "red-pin-icon-id"
    private var mapView: MapView? = null
    private var currentRoute: DirectionsRoute? = null
    private var client: MapboxDirections? = null
    private var origin: Point? = null
    private var destination: Point? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.access_token))
        setContentView(R.layout.activity_try)

        val preferences = getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
        val borneDLal = preferences?.getDouble("borneDLal", 0.0)
        val borneDLong = preferences?.getDouble("borneDLong", 0.0)

        val borneFLal = preferences?.getDouble("borneFLal", 0.0)
        val borneFLong = preferences?.getDouble("borneFLong", 0.0)

        mapView = findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync(OnMapReadyCallback() {

            fun onMapReady(mapboxMap: MapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, OnStyleLoaded {

                    fun onStyleLoaded(style : Style) {

                        /*origin = Point.fromLngLat(borneDLong!!, borneDLal!!);
                        destination = Point.fromLngLat(borneFLong!!, borneFLal!!);*/

                        origin = Point.fromLngLat(-3.588098, 37.176164);

                        destination = Point.fromLngLat(-3.601845, 37.184080);

                        initSource(style);

                        initLayers(style);

                        getRoute(mapboxMap, origin!!, destination!!)
                    }
                });
            }
        });
    }

    private fun initSource(loadedMapStyle: Style) {
        loadedMapStyle.addSource(GeoJsonSource(ROUTE_SOURCE_ID))
        val iconGeoJsonSource = GeoJsonSource(
            ICON_SOURCE_ID, FeatureCollection.fromFeatures(
                arrayOf<Feature>(
                    Feature.fromGeometry(
                        Point.fromLngLat(
                            origin!!.longitude(),
                            origin!!.latitude()
                        )
                    ),
                    Feature.fromGeometry(
                        Point.fromLngLat(
                            destination!!.longitude(),
                            destination!!.latitude()
                        )
                    )
                )
            )
        )
        loadedMapStyle.addSource(iconGeoJsonSource)
    }

    private fun initLayers(loadedMapStyle: Style) {
        val routeLayer = LineLayer(ROUTE_LAYER_ID, ROUTE_SOURCE_ID).also {

            it.setProperties(
                lineCap(Property.LINE_CAP_ROUND),
                lineJoin(Property.LINE_JOIN_ROUND),
                lineWidth(5f),
                lineColor(Color.parseColor("#009688"))
            )
        }
        loadedMapStyle.addLayer(routeLayer)

        loadedMapStyle.addImage(
            RED_PIN_ICON_ID, BitmapUtils.getBitmapFromDrawable(
                resources.getDrawable(R.drawable.ic_red_marker)
            )!!
        )

        loadedMapStyle.addLayer(
            SymbolLayer(ICON_LAYER_ID, ICON_SOURCE_ID).withProperties(
                iconImage(RED_PIN_ICON_ID),
                iconIgnorePlacement(true),
                iconAllowOverlap(true),
                iconOffset(arrayOf(0f, -9f))
            )
        )
    }

    private fun getRoute(mapboxMap: MapboxMap?, origin: Point, destination: Point) {
        client = MapboxDirections.builder()
            .origin(origin)
            .destination(destination)
            .overview(DirectionsCriteria.OVERVIEW_FULL)
            .profile(DirectionsCriteria.PROFILE_DRIVING)
            .accessToken(getString(R.string.access_token))
            .build()

        client?.enqueueCall(object : Callback<DirectionsResponse> {
            override fun onResponse(
                call: Call<DirectionsResponse>,
                response: Response<DirectionsResponse>
            ) {

                if (response.body() == null) {
                    println("No routes found, make sure you set the right user and access token.")
                    return
                } else if (response.body()!!.routes().size < 1) {
                    println("No routes found")
                    return
                }

                currentRoute = response.body()!!.routes()[0]

                mapboxMap?.getStyle { style ->
                    val source = style.getSourceAs<GeoJsonSource>(ROUTE_SOURCE_ID)

                    source?.setGeoJson(
                        LineString.fromPolyline(
                            currentRoute?.geometry()!!,
                            PRECISION_6
                        )
                    )
                }
            }

            override fun onFailure(call: Call<DirectionsResponse>, throwable: Throwable) {

                println("Error: " + throwable.message)
            }
        })
    }
}



