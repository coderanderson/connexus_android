package us.connex.miniprojectapt.Remote;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.Url;
import us.connex.miniprojectapt.Model.ByteMessage;
import us.connex.miniprojectapt.Model.Search;
import us.connex.miniprojectapt.Model.UploadPhoto;
import us.connex.miniprojectapt.Model.UploadSession;
import us.connex.miniprojectapt.Model.ViewAll;
import us.connex.miniprojectapt.Model.ViewNearby;
import us.connex.miniprojectapt.Model.ViewSingle;

public interface StreamService {

    @POST("/api/all_streams")
    Call<List<ViewAll>> getViewAll_Obj();

    @POST("/api/all_streams")
    Call<List<Search>> getSearchResult_Obj(@Query("query") String string);

    @POST("/api/view")
    Call<ViewSingle> getSingleStream_Obj(@Query("stream_name") String string);

    @POST("/api/nearby_photo")
    Call<ViewNearby> getNearbyData_Obj(@Query("lat") double lat,
                                       @Query("lon") double lon,
                                       @Query("All") int All);


    @POST("/api/upload_photo")
    Call<UploadSession> uploadPhotoSession_Obj();

    @POST
    Call<UploadPhoto> uploadPhoto_Obj(@Url String uploadUrl, @Part("file") ByteMessage image,
            @Part("stream_name") String streamName, @Part("lat") String lat, @Part("lon") String lon);
}
