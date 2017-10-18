package samples.iivchenko.mappathtest.models;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public enum RouteInstance {
    INSTANCE;
    private static RouteModel route;
    private static PolylineOptions line = new PolylineOptions();
    private static LatLngBounds.Builder latLngBuilder = new LatLngBounds.Builder();
    private static MarkerOptions startMarkerOptions = new MarkerOptions();
    private static MarkerOptions endMarkerOptions = new MarkerOptions();


    public static void calculateMarkers() {
        for (int i = 0; i < route.size(); i++) {
            if (i == 0) {
                MarkerOptions startMarkerOptions = new MarkerOptions()
                        .position(route.getPoint(i));
            } else if (i == route.size() - 1) {
                MarkerOptions endMarkerOptions = new MarkerOptions()
                        .position(route.getPoint(i));
            }
            line.add(route.getPoint(i));
            latLngBuilder.include(route.getPoint(i));
        }

    }

    public static MarkerOptions getStartMarkerOptions() {
        if (startMarkerOptions == null) {
            calculateMarkers();
        }
        return startMarkerOptions;
    }

    public static MarkerOptions getEndMarkerOptions() {
        if (endMarkerOptions == null) {
            calculateMarkers();
        }
        return endMarkerOptions;
    }

    public static RouteModel getRoute() {
        return route;
    }

    public static void setRoute(RouteModel route) {
        RouteInstance.route = route;
    }

    public static PolylineOptions getLine() {
        if (line == null) {
            calculateMarkers();
        }
        return line;
    }

    public static void setLine(PolylineOptions line) {
        RouteInstance.line = line;
    }

    public static LatLngBounds.Builder getLatLngBuilder() {
        return latLngBuilder;
    }

    public static void setLatLngBuilder(LatLngBounds.Builder latLngBuilder) {
        RouteInstance.latLngBuilder = latLngBuilder;
    }
}
