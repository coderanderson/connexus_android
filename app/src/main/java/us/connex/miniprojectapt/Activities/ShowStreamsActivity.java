package us.connex.miniprojectapt.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.connex.miniprojectapt.Model.PhotoLocationes;
import us.connex.miniprojectapt.Model.Search;
import us.connex.miniprojectapt.Model.ViewAll;
import us.connex.miniprojectapt.Model.ViewSingle;
import us.connex.miniprojectapt.R;
import us.connex.miniprojectapt.Remote.RetrofitClient;
import us.connex.miniprojectapt.Remote.StreamService;
import us.connex.miniprojectapt.Adapters.StreamImageAdapter;

import static us.connex.miniprojectapt.Model.Constant.BASE_URL;
import static us.connex.miniprojectapt.Model.Constant.EXTRA_MESSAGE;

public class ShowStreamsActivity extends AppCompatActivity {

    private GridView gridview;
    private SearchView searchView;
    private StreamImageAdapter imageAdapter;
    private StreamService myStreamService;
    private List<Stream> streams = new ArrayList<>();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.streams_view);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(true);
        context = getApplicationContext();
        request_streams();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(context, "begin search: " + query, Toast.LENGTH_SHORT).show();
                myStreamService = getStreamService();
                getStreamSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    public void request_streams() {
        myStreamService = getStreamService();
        getStream();
    }

    public void showNearby(View view) {
        Intent intent = new Intent(ShowStreamsActivity.this, ShowNearbyActivity.class);
        ArrayList<String> allStreamNames = new ArrayList<>();
        for(int i = 0;i < streams.size();i++)
            allStreamNames.add(streams.get(i).getName());
        intent.putStringArrayListExtra("all_stream_names", allStreamNames);
        startActivity(intent);
    }

    private void getStream() {
        myStreamService.getViewAll_Obj().enqueue(new Callback<List<ViewAll>>() {
            @Override
            public void onResponse(Call<List<ViewAll>> call, Response<List<ViewAll>> response) {
                List<ViewAll> list = response.body();
                for (int i = 0; i < list.size(); i++) {
                    streams.add(new Stream(list.get(i).getName(), list.get(i).getCoverUrl()));
                }
                gridview = (GridView) findViewById(R.id.gridview);
                imageAdapter = new StreamImageAdapter(ShowStreamsActivity.this, streams);
                gridview.setAdapter(imageAdapter);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        String streamName = streams.get(position).getName();
                        myStreamService = getStreamService();
                        getSingleStream(streamName);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ViewAll>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    private void getSingleStream(final String string) {
        myStreamService.getSingleStream_Obj(string).enqueue(new Callback<ViewSingle>() {
            @Override
            public void onResponse(Call<ViewSingle> call, Response<ViewSingle> response) {
                ViewSingle viewsingle = response.body();
                ArrayList<String> photoUrls = viewsingle.getPhotoUrls();
                Intent intent = new Intent(ShowStreamsActivity.this, ShowSingleStreamActivity.class);
                intent.putExtra(EXTRA_MESSAGE, string);
                intent.putStringArrayListExtra("photo_urls", photoUrls);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ViewSingle> call, Throwable t) {

            }
        });
    }

    //this is a test for search view
    public void searchView(View view) {
        Intent intent = new Intent(ShowStreamsActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    private void getStreamSearch(final String string) {
        myStreamService.getSearchResult_Obj(string).enqueue(new Callback<List<Search>>() {
            @Override
            public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
                List<Search> list = response.body();
                ArrayList<String> name_list = new ArrayList<>();
                ArrayList<String> cover_url_list = new ArrayList<>();
                Intent intent = new Intent(ShowStreamsActivity.this, SearchActivity.class);
                if(list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        name_list.add(list.get(i).getName());
                        cover_url_list.add(list.get(i).getCoverUrl());
                    }
                    intent.putStringArrayListExtra("name_list", name_list);
                    intent.putStringArrayListExtra("cover_url_list", cover_url_list);
                }
                intent.putExtra("query", string);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Search>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    public static StreamService getStreamService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }
}
