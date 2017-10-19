package samples.iivchenko.mappathtest.models;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class RouteModelTest {

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
        RouteModel routeThatGotBack = RouteInstance.INSTANCE.getRoute();
        assertEquals(routeModel, routeThatGotBack);
    }

    @Test
    public void getPointTest() {
        LatLng actual = RouteInstance.INSTANCE.getRoute().getPoint(2);
        LatLng expected = new LatLng(40.00, 10.00);
        assertEquals(expected, actual);
    }

    @Test
    public void sizeTest() {
        int actual = RouteInstance.INSTANCE.getRoute().size();
        assertEquals(3, actual);
    }

}
