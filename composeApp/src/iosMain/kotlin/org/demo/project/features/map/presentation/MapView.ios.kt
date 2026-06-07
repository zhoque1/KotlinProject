package org.demo.project.features.map.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreLocation.CLLocationCoordinate2DMake
import platform.MapKit.MKCoordinateRegionMakeWithDistance
import platform.MapKit.MKMapView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun MapView(modifier: Modifier) {
    UIKitView(
        factory = { MKMapView() },
        modifier = modifier,
        update = {
            val singaporeCoordinate = CLLocationCoordinate2DMake(1.35, 103.87)
            val region = MKCoordinateRegionMakeWithDistance(singaporeCoordinate, 10000.0, 10000.0)
            it.setRegion(region, animated = false)
        }
    )
}
