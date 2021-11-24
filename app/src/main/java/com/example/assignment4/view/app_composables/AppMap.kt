package com.example.assignment4.view.app_composables

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.scale
import com.example.assignment4.R
import com.example.assignment4.utils.Permission
import com.example.assignment4.utils.map.Coordinate
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.addOnMapClickListener
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.scalebar.scalebar

// Take a list of coordinates
// trigger callback when an annotation is pressed
@SuppressLint("MissingPermission")
@ExperimentalPermissionsApi
@Composable
fun AppMap(
    modifier: Modifier = Modifier,
    coordinates: List<Coordinate> = listOf(),
    onAnnotationPress: (coordinate: Coordinate) -> Unit = {}
) {
    var mapBox by remember { mutableStateOf<MapboxMap?>(null) }
    var mapView by remember { mutableStateOf<MapView?>(null) }

    // Require User permission to access user location
    Permission(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        rationale = "Need your permission for map functioning properly",
        permissionNotAvailableContent = {
            Text("Map permission is not enable")
        }
    ) {
        // Location Permission granted!
        AndroidView(
            modifier = modifier,
            factory = { context ->
                MapView(context).apply {
                    mapView = this
                    mapBox = this.getMapboxMap()

                    // Set Android View Styling
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )

                    // Set user location style
                    mapBox?.loadStyleUri(Style.MAPBOX_STREETS) {
                        // hide scalebar
                        this.scalebar.enabled = false

                        // user location setting
                        this.location.updateSettings {
                            this.enabled = true
                            pulsingEnabled = true
                        }
                    }

                    val pointAnnotationManager = annotations.createPointAnnotationManager(this)
                    val bitmap = BitmapFactory
                        .decodeResource(resources, R.drawable.banana_map_pin)
                        .scale(150, 150)

                    // Pin each location on the map
                    for (coordinate in coordinates) {
                        var point = Point.fromLngLat(
                            coordinate.longitude,
                            coordinate.latitude
                        )

                        val pointAnnotationOptions = PointAnnotationOptions()
                            .withPoint(point)
                            .withIconImage(bitmap)

                        pointAnnotationManager.create(pointAnnotationOptions)
                    }

                    // add onAnnotationPress listener
                    pointAnnotationManager.addClickListener {
                        onAnnotationPress(
                            Coordinate(
                                latitude = it.point.latitude(),
                                longitude = it.point.longitude()
                            )
                        )

                        // expect a boolean return
                        true
                    }

                    // Get User Current Location and fly to that location
                    LocationServices.getFusedLocationProviderClient(context)
                        .lastLocation.addOnCompleteListener {
                            var latitude = it.result.latitude
                            var longitude = it.result.longitude

                            var currentPoint = Point.fromLngLat(
                                longitude,
                                latitude
                            )

                            mapBox!!.flyTo(
                                cameraOptions {
                                    center(currentPoint)
                                    zoom(10.0)
                                },
                                MapAnimationOptions.mapAnimationOptions {
                                    duration(3000)
                                }
                            )
                        }
                }
            }
        )
    }
}