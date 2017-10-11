package samples.iivchenko.mappathtest.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class RouteModel {

    public List<Points> coords;

    public int size() {
        return this.coords.size();
    }

    public LatLng getPoint(int pos) {
        LatLng ll = new LatLng(this.coords.get(pos).getLa(), this.coords.get(pos).getLo());
        return ll;
    }

    public class Points {
        double la;
        double lo;

        public double getLa() {
            return la;
        }

        public double getLo() {
            return lo;
        }
    }
}