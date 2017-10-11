package samples.iivchenko.mappathtest.ui;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import samples.iivchenko.mappathtest.R;
import samples.iivchenko.mappathtest.models.RouteModel;

public class PolyLinePainter {
    private static final String LOG_TAG = "PolyLinePainterLog";

    public void paint(Context context, GoogleMap mGoogleMap, RouteModel routeModel) {
        RouteModel mPoints = routeModel;
        PolylineOptions line = new PolylineOptions();
        line.width(4f).color(R.color.indigo900);
        LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
        try {
            for (int i = 0; i < mPoints.size(); i++) {
                MarkerOptions startMarkerOptions = new MarkerOptions()
                        .position(mPoints.getPoint(i));
                mGoogleMap.addMarker(startMarkerOptions);

                line.add(mPoints.getPoint(i));
                latLngBuilder.include(mPoints.getPoint(i));
            }
        } catch (Exception e) {
            Log.d(LOG_TAG, e.toString());
        }
        mGoogleMap.addPolyline(line);
        int sizeW = context.getResources().getDisplayMetrics().widthPixels;
        int sizeH = context.getResources().getDisplayMetrics().heightPixels;

        LatLngBounds latLngBounds = latLngBuilder.build();
        CameraUpdate track = CameraUpdateFactory.newLatLngBounds(latLngBounds, sizeW, sizeH, 30);
        mGoogleMap.moveCamera(track);
    }
}
