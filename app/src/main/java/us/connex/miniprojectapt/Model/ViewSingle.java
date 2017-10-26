package us.connex.miniprojectapt.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmk on 2017/10/26.
 */

public class ViewSingle {

    @SerializedName("stream_name")
    private String streamName;

    @SerializedName("photo_dates")
    private ArrayList<String> photoDates;

    @SerializedName("photo_locations")
    private ArrayList<PhotoLocationes> photoLocationses;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("photo_urls")
    private ArrayList<String> photoUrls;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("page")
    private int page;

    @SerializedName("cover_url")
    private String coverUrl;

    public String getStreamName() {
        return streamName;
    }

    public ArrayList<String> getPhotoDates() {
        return photoDates;
    }

    public ArrayList<PhotoLocationes> getPhotoLocationses() {
        return photoLocationses;
    }

    public int getPerPage() {
        return perPage;
    }

    public ArrayList<String> getPhotoUrls() {
        return photoUrls;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPage() {
        return page;
    }

    public String getCoverUrl() {
        return coverUrl;
    }
}
