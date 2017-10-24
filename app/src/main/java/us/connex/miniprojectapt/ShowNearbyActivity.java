package us.connex.miniprojectapt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dranderson on 10/24/17.
 */

public class ShowNearbyActivity extends AppCompatActivity {
    private GridView gridview;
    private ImageGpsAdaptor imageAdapter;
    private List<ImageWithGps> images;
    private int seeMoreClicked = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_nearby_stream);
        images = new ArrayList<>();
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
        seeMoreClicked++;
    }

    public void seeMoreImages(View view) {
        imageAdapter = new ImageGpsAdaptor(this, images, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        seeMoreClicked++;
    }
}
