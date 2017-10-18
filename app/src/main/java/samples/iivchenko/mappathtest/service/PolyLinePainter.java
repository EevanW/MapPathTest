package samples.iivchenko.mappathtest.service;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

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
            for (int i = 0; i < routeModel.size(); i++) {
                if (i == 0) {
                    MarkerOptions startMarkerOptions = new MarkerOptions()
                            .position(routeModel.getPoint(i));
                    mGoogleMap.addMarker(startMarkerOptions);
                } else if (i == routeModel.size() - 1) {
                    MarkerOptions endMarkerOptions = new MarkerOptions()
                            .position(routeModel.getPoint(i));
                    mGoogleMap.addMarker(endMarkerOptions);
                }
                RouteInstance.INSTANCE.getLine().add(routeModel.getPoint(i));
                RouteInstance.INSTANCE.getLatLngBuilder().include(routeModel.getPoint(i));
            }
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
