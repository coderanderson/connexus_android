package us.connex.miniprojectapt.Activities;

import android.util.Log;

import java.util.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.connex.miniprojectapt.Model.ViewAll;
import us.connex.miniprojectapt.Remote.RetrofitClient;
import us.connex.miniprojectapt.Remote.StreamService;

import static us.connex.miniprojectapt.Model.Constant.BASE_URL;

/**
 * Created by dranderson on 10/22/17.
 */

public class fetch_all_streams {
    StreamService myStreamService;
    private List<Stream> streams = new ArrayList<Stream>();

    public void request_streams() {
        myStreamService = getStreamService();
        getStream();
    }

    private void getStream() {
        myStreamService.getViewAll_Obj().enqueue(new Callback<List<ViewAll>>() {
            @Override
            public void onResponse(Call<List<ViewAll>> call, Response<List<ViewAll>> response) {
                List<ViewAll> list = response.body();
                System.out.println(list.size());
                for(int i = 0;i < list.size();i++) {
                    streams.add(new Stream(list.get(i).getName(), list.get(i).getCoverUrl()));
                }
            }

            @Override
            public void onFailure(Call<List<ViewAll>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    public Stream[] getStreams() {
        Stream[] fetchedStreams = new Stream[streams.size()];
        for(int i = 0; i < fetchedStreams.length; i++) {
            fetchedStreams[i] = streams.get(i);
        }
        return fetchedStreams;
    }

    public static StreamService getStreamService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }
}
