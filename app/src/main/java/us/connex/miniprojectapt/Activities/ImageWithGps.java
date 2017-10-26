package us.connex.miniprojectapt.Activities;

/**
 * Created by dranderson on 10/24/17.
 */

public class ImageWithGps {
    private String imageURL;
    private String streamName;
    private String distance;
    public ImageWithGps(String imageURL, String streamName, String distance) {
        this.imageURL = imageURL;
        this.streamName = streamName;
        this.distance = distance;
    }
    public String getURL() { return imageURL; }
    public String getStreamName() { return streamName; }
    public String getDistance() { return distance; }
}
