package us.connex.miniprojectapt.Model;

/**
 * Created by cmk on 2017/10/26.
 */

public class PhotoLocationes {
    private double lat;
    private double lon;
    PhotoLocationes(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
