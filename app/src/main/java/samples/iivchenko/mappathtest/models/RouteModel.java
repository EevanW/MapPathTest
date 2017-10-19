package samples.iivchenko.mappathtest.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class RouteModel {

    private List<RoutePoint> coords;

    public RouteModel() {
    }

    public void addPoint(double la, double lo) {
        if (this.coords == null) {
            this.coords = new ArrayList<>();
        }
        this.coords.add(new RoutePoint(la, lo));
    }

    public int size() {
        return this.coords.size();
    }

    public LatLng getPoint(int pos) {
        LatLng ll = new LatLng(this.coords.get(pos).getLa(), this.coords.get(pos).getLo());
        return ll;
    }

    class RoutePoint {
        private double la;
        private double lo;

        RoutePoint(double la, double lo) {
            this.la = la;
            this.lo = lo;
        }

        double getLa() {
            return la;
        }

        double getLo() {
            return lo;
        }
    }
}