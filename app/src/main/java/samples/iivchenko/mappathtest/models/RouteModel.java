package samples.iivchenko.mappathtest.models;

import java.util.List;

public class RouteModel {

    public List<Points> coords;

    public Points getPoint(int pos) {
        return this.coords.get(pos);
    }

    class Points {
        String la;
        String lo;

        public String getLa() {
            return la;
        }

        public String getLo() {
            return lo;
        }
    }
}