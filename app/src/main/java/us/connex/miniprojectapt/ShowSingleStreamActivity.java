package us.connex.miniprojectapt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by dranderson on 10/21/17.
 */

public class ShowSingleStreamActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "stream name";
    private GridView gridview;
    private SingleImageAdapter imageAdapter;
    private String streamName;
    private String[] imageURLs;
    private int seeMoreClicked = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_stream);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        streamName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_0);
        String message = "View A Stream: " + streamName;
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
        gridview = (GridView) findViewById(R.id.gridview);
        imageAdapter = new SingleImageAdapter(this, imageURLs, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        seeMoreClicked++;
    }
    //TODO: fetch image urls from server according to stream name;
    private void fetchImages() {
        imageURLs = new String[0];
    }


    public void seeStreams(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void seeMoreImages(View view) {
        imageAdapter = new SingleImageAdapter(this, imageURLs, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        seeMoreClicked++;
    }

    public void imageUpload(View view) {
        Intent intent = new Intent(this, ImageUploadActivity.class);
        intent.putExtra(EXTRA_MESSAGE, streamName);
        startActivity(intent);
    }
}
