package us.connex.miniprojectapt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dranderson on 10/24/17.
 */

public class ShowNearbyActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "stream name";

    private GridView gridview;
    private ImageGpsAdaptor imageAdapter;
    private List<ImageWithGps> images;
    private int seeMoreClicked = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_nearby_stream);
        images = new ArrayList<>();

        //TODO: request nearby images from server, sending local coordinate
        //image placeholders:
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig1", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://d3jkudlc7u70kh.cloudfront.net/interesting-guinea-pig-behavior-fact.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://upload.wikimedia.org/wikipedia/commons/0/00/Two_adult_Guinea_Pigs_%28Cavia_porcellus%29.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://upload.wikimedia.org/wikipedia/commons/0/00/Two_adult_Guinea_Pigs_%28Cavia_porcellus%29.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://upload.wikimedia.org/wikipedia/commons/0/00/Two_adult_Guinea_Pigs_%28Cavia_porcellus%29.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://upload.wikimedia.org/wikipedia/commons/0/00/Two_adult_Guinea_Pigs_%28Cavia_porcellus%29.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://upload.wikimedia.org/wikipedia/commons/0/00/Two_adult_Guinea_Pigs_%28Cavia_porcellus%29.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://upload.wikimedia.org/wikipedia/commons/0/00/Two_adult_Guinea_Pigs_%28Cavia_porcellus%29.jpg", "pig2", "1"));
        images.add(new ImageWithGps("https://upload.wikimedia.org/wikipedia/commons/0/00/Two_adult_Guinea_Pigs_%28Cavia_porcellus%29.jpg", "pig2", "1"));


        gridview = (GridView) findViewById(R.id.gridview);
        imageAdapter = new ImageGpsAdaptor(this, images, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent intent = new Intent(ShowNearbyActivity.this, ShowSingleStreamActivity.class);
                intent.putExtra(EXTRA_MESSAGE, images.get(position).getStreamName());
                startActivity(intent);
            }
        });
        seeMoreClicked++;
    }

    public void seeMoreImages(View view) {
        imageAdapter = new ImageGpsAdaptor(this, images, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        if(images.size() > 16 && seeMoreClicked < (images.size() - 1) / 16) {
            seeMoreClicked++;
        }
    }

    public void seeStreams(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
