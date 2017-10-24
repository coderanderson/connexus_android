package us.connex.miniprojectapt;

/**
 * Created by dranderson on 10/24/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by cmk on 2017/10/21.
 */

public interface StreamService {

    @POST("/api/all_streams")
    Call<List<ViewAll>> getViewAll_Obj();
}
