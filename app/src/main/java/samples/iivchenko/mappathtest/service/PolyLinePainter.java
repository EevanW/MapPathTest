package samples.iivchenko.mappathtest.service;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;

import samples.iivchenko.mappathtest.R;
import samples.iivchenko.mappathtest.models.RouteInstance;
import samples.iivchenko.mappathtest.models.RouteModel;

public class PolyLinePainter {
    private static final String LOG_TAG = "PolyLinePainterLog";

    public void paint(GoogleMap mGoogleMap, RouteModel routeModel) {
        RouteInstance.INSTANCE.setRoute(routeModel);
        RouteInstance.INSTANCE.getLine().width(4f).color(R.color.indigo900);
        calculateMarkers(mGoogleMap, routeModel);
        paintRoute(mGoogleMap);
    }

    public void calculateMarkers(GoogleMap mGoogleMap, RouteModel routeModel) {
        try {
            RouteInstance.INSTANCE.setRoute(routeModel);
            RouteInstance.INSTANCE.calculateRouteParams();
            mGoogleMap.addMarker(RouteInstance.INSTANCE.getStartMarkerOptions());
            mGoogleMap.addMarker(RouteInstance.INSTANCE.getEndMarkerOptions());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.toString());
        }
    }

    public void paintRoute(GoogleMap mGoogleMap) {
        mGoogleMap.addPolyline(RouteInstance.INSTANCE.getLine());
        LatLngBounds latLngBounds = RouteInstance.INSTANCE.getLatLngBuilder().build();
        CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, 15);
        mGoogleMap.moveCamera(track);
    }
}
