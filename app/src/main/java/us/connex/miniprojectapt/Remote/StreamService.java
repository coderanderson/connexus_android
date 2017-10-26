package us.connex.miniprojectapt.Remote;

/**
 * Created by dranderson on 10/24/17.
 */

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import us.connex.miniprojectapt.Model.Search;
import us.connex.miniprojectapt.Model.ViewAll;
import us.connex.miniprojectapt.Model.ViewSingle;

/**
 * Created by cmk on 2017/10/21.
 */

public interface StreamService {

    @POST("/api/all_streams")
    Call<List<ViewAll>> getViewAll_Obj();

    @POST("/api/all_streams")
    Call<List<Search>> getSearchResult_Obj(@Query("query") String string);

    @POST("/api/view")
    Call<ViewSingle> getSingleStream_Obj(@Query("stream_name") String string);
}
