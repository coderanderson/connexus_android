package us.connex.miniprojectapt;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dranderson on 10/24/17.
 */

public class SearchActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "stream name";
    private static final String BASE_URL = "https://apt-s17-am79848.appspot.com/";
    private TextView textView;
    private Context context;
    private SearchView searchView;
    private String streamName;
    private StreamService myStreamService;

    private GridView gridview;
    private SearchImageAdaptor imageAdapter;
    private int seeMoreClicked = 0;
    private List<Stream> streams;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);
        //TODO: get streamName and resultNum and streams from server;
        List<String> name_list;
        List<String> cover_url_list;

        Intent intent = getIntent();
        streamName = intent.getStringExtra("query");
        name_list = intent.getStringArrayListExtra("name_list");
        cover_url_list = intent.getStringArrayListExtra("cover_url_list");
        streams = new ArrayList<Stream>();
        for(int i = 0; i < name_list.size(); i++) {
            streams.add(new Stream(name_list.get(i), cover_url_list.get(i)));
        }
        textView = (TextView) findViewById(R.id.textView1);
        textView.setText("" + streams.size() + " results for " + streamName);

        gridview = (GridView) findViewById(R.id.gridview);
        searchView = (SearchView) findViewById(R.id.searchView);
        context = getApplicationContext();

        imageAdapter = new SearchImageAdaptor(this, streams, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, ShowSingleStreamActivity.class);
                intent.putExtra(EXTRA_MESSAGE, streams.get(position).getName());
                startActivity(intent);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(context, "begin search: "+query, Toast.LENGTH_SHORT).show();
                myStreamService = getStreamService();
                getStream(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void getStream(final String string) {
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
                Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
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

    public static StreamService getStreamService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }
}
