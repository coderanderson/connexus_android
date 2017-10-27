package us.connex.miniprojectapt.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by cmk on 2017/10/27.
 */

public class ViewNearby {
    @SerializedName("photos")
    private List<Photo> photos;

    private int All;
    private double lat;
    private double lon;

    public ViewNearby(int All, double lat, double lon) {
        this.All = All;
        this.lat = lat;
        this.lon = lon;
    }

    public List<Photo> getPhotos() {
        return photos;
    }
}
