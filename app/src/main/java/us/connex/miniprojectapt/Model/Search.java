package us.connex.miniprojectapt.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dranderson on 10/24/17.
 */

public class Search {
    @SerializedName("name")
    private String name;

    @SerializedName("cover_url")
    private String coverUrl;

    public String getName() {
        return name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

}