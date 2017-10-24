package us.connex.miniprojectapt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dranderson on 10/24/17.
 */

public class SearchActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "stream name";
    private TextView textView;
    private String streamName;
    private int resultNum;
    private GridView gridview;
    private SearchImageAdaptor imageAdapter;
    private int seeMoreClicked = 0;
    private List<Stream> streams;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_view);
        //TODO: get streamName and resultNum and streams from server;
        //placeholder:
        resultNum = 123;
        streamName = "Logan";
        streams = new ArrayList<Stream>();
        textView = (TextView) findViewById(R.id.textView1);
        textView.setText("" + resultNum + " results for " + streamName);

        gridview = (GridView) findViewById(R.id.gridview);
        imageAdapter = new SearchImageAdaptor(this, streams, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, ShowSingleStreamActivity.class);
                intent.putExtra(EXTRA_MESSAGE, streams.get(position).getName());
                startActivity(intent);
            }
        });
    }
}
