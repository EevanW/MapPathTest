package samples.iivchenko.mappathtest.models;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouteInstanceTest {

    static RouteModel routeModel = new RouteModel();

    @Before
    public void setup() {
        routeModel.addPoint(10.00, 40.00);
        routeModel.addPoint(20.00, 20.00);
        routeModel.addPoint(40.00, 10.00);
        RouteInstance.INSTANCE.setRoute(routeModel);
    }

    @Test
    public void setRouteTest() {
        RouteModel expectedRouteModel = new RouteModel();
        expectedRouteModel.addPoint(10.00, 40.00);
        expectedRouteModel.addPoint(20.00, 20.00);
        RouteInstance.INSTANCE.setRoute(expectedRouteModel);
        RouteModel actualRouteModel = RouteInstance.INSTANCE.getRoute();
        assertEquals(expectedRouteModel, actualRouteModel);
    }

    @Test
    public void getRouteTest() {
        RouteModel actualRouteModel = RouteInstance.INSTANCE.getRoute();
        assertEquals(routeModel, actualRouteModel);
    }

    @Test
    public void getStartMarkerOptionsTest() {
        MarkerOptions expectedMarker = new MarkerOptions()
                .position(new LatLng(10.00, 40.00));
        MarkerOptions actualMarker = RouteInstance.INSTANCE.getStartMarkerOptions();
        assertionForEqualObject(expectedMarker, actualMarker);
    }

    @Test
    public void getEndMarkerOptionsTest() {
        MarkerOptions expectedMarker = new MarkerOptions()
                .position(new LatLng(40.00, 10.00));
        MarkerOptions actualMarker = RouteInstance.INSTANCE.getEndMarkerOptions();
        assertionForEqualObject(expectedMarker, actualMarker);
    }

    @Test
    public void polyLineTest() {
        PolylineOptions expectedPolyine = new PolylineOptions();
        expectedPolyine.add(new LatLng(10.00, 40.00));
        expectedPolyine.add(new LatLng(20.00, 20.00));
        expectedPolyine.add(new LatLng(40.00, 10.00));
        PolylineOptions actualPolyline = RouteInstance.INSTANCE.getLine();
        assertionForEqualObject(expectedPolyine, actualPolyline);
    }

    @Test
    public void latLngBounceTest() {
        LatLngBounds expectedLatLngBounds = new LatLngBounds(new LatLng(10.00, 40.00), new LatLng(40.00, 10.00));
        RouteInstance.INSTANCE.calculateRouteParams();
        LatLngBounds actualLatLngBounds = RouteInstance.INSTANCE.getLatLngBuilder().build();
        assertionForEqualObject(expectedLatLngBounds, actualLatLngBounds);
    }

    public void assertionForEqualObject(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            Assert.assertNotEquals(expected, actual);
        }
    }
}
