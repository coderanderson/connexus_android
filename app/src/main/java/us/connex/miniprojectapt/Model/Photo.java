package us.connex.miniprojectapt.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by cmk on 2017/10/27.
 */

public class Photo {
    @SerializedName("distance_ft")
    private double distance;

    @SerializedName("url")
    private String photoUrl;

    @SerializedName("stream_name")
    private String streamName;

    public double getDistance() {
        return distance;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getStreamName() {
        return streamName;
    }
}
