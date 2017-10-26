package us.connex.miniprojectapt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE_0 = "stream name";
    private GridView gridview;
    private SearchView searchView;
    private StreamImageAdapter imageAdapter;
    StreamService myStreamService;
    private static final String BASE_URL = "https://apt-s17-am79848.appspot.com/";
    private List<Stream> streams = new ArrayList<Stream>();
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setIconifiedByDefault(false);
        context = getApplicationContext();
        request_streams();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(context, "begin search: "+query, Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(MainActivity.this, ShowNearbyActivity.class);
        startActivity(intent);
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
                gridview = (GridView) findViewById(R.id.gridview);
                imageAdapter = new StreamImageAdapter(MainActivity.this, streams);
                gridview.setAdapter(imageAdapter);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                        Intent intent = new Intent(MainActivity.this, ShowSingleStreamActivity.class);
                        intent.putExtra(EXTRA_MESSAGE_0, streams.get(position).getName());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<ViewAll>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }


    public static StreamService getStreamService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }

    //this is a test for search view
    public void searchView(View view) {
        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    private void getStreamSearch(final String string) {
        myStreamService.getSearchResult_Obj(string).enqueue(new Callback<List<Search>>() {
            @Override
            public void onResponse(Call<List<Search>> call, Response<List<Search>> response) {
                List<Search> list = response.body();
                ArrayList<String> name_list = new ArrayList<>();
                ArrayList<String> cover_url_list = new ArrayList<>();
                for(int i = 0;i < list.size();i++) {
                    name_list.add(list.get(i).getName());
                    cover_url_list.add(list.get(i).getCoverUrl());
                }
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putStringArrayListExtra("name_list", name_list);
                intent.putStringArrayListExtra("cover_url_list", cover_url_list);
                intent.putExtra("query", string);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Search>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    public static StreamService getStreamSearchService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }
}

