package us.connex.miniprojectapt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "stream name";
    private GridView gridview;
    private StreamImageAdapter imageAdapter;
    private Stream[] streams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetch_all_streams fetchAllStreams = new fetch_all_streams();
        fetchAllStreams.request_streams();
        streams = fetchAllStreams.getStreams();

        gridview = (GridView) findViewById(R.id.gridview);
        imageAdapter = new StreamImageAdapter(this, streams);
        gridview.setAdapter(imageAdapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ShowSingleStreamActivity.class);
                intent.putExtra(EXTRA_MESSAGE, streams[position].getName());
                startActivity(intent);
            }
        });
    }
}

