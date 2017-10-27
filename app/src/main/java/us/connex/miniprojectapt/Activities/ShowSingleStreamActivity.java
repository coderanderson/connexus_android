package us.connex.miniprojectapt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

import us.connex.miniprojectapt.R;
import us.connex.miniprojectapt.Adapters.SingleImageAdapter;

import static us.connex.miniprojectapt.Model.Constant.BASE_URL;
import static us.connex.miniprojectapt.Model.Constant.EXTRA_MESSAGE;

/**
 * Created by dranderson on 10/21/17.
 */

public class ShowSingleStreamActivity extends AppCompatActivity {

    private GridView gridview;
    private SingleImageAdapter imageAdapter;
    private String streamName;
    private ArrayList<String> imageURLs;
    private int seeMoreClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_single_stream);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        streamName = intent.getStringExtra(EXTRA_MESSAGE);
        imageURLs = intent.getStringArrayListExtra("photo_urls");
        String message = "View A Stream: " + streamName;
        System.out.println(imageURLs.get(0));
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);
        gridview = (GridView) findViewById(R.id.gridview);
        imageAdapter = new SingleImageAdapter(this, imageURLs, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        seeMoreClicked++;
    }
    //TODO: fetch image urls from server according to stream name;
    /*private void fetchImages() {
        imageURLs = new String[0];
    }
    */


    public void seeStreams(View view) {
        Intent intent = new Intent(this, ShowStreamsActivity.class);
        startActivity(intent);
    }

    public void seeMoreImages(View view) {
        imageAdapter = new SingleImageAdapter(this, imageURLs, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        if(imageURLs.size() > 16 && seeMoreClicked < (imageURLs.size() - 1) / 16) {
            seeMoreClicked++;
        }
    }

    public void imageUpload(View view) {
        Intent intent = new Intent(this, ImageUploadActivity.class);
        intent.putExtra(EXTRA_MESSAGE, streamName);
        startActivity(intent);
    }
}
