package us.connex.miniprojectapt.Model;

import com.google.gson.annotations.SerializedName;

public class UploadSession {
    @SerializedName("new_url")
    private String newUploadUrl;

    public String getNewUploadUrl() {
        return newUploadUrl;
    }

}