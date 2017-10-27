package us.connex.miniprojectapt.Activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.jar.Manifest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.connex.miniprojectapt.Adapters.ImageGpsAdaptor;
import us.connex.miniprojectapt.Model.GPStracker;
import us.connex.miniprojectapt.Model.Photo;
import us.connex.miniprojectapt.Model.PhotoLocationes;
import us.connex.miniprojectapt.Model.ViewNearby;
import us.connex.miniprojectapt.Model.ViewSingle;
import us.connex.miniprojectapt.R;
import us.connex.miniprojectapt.Remote.RetrofitClient;
import us.connex.miniprojectapt.Remote.StreamService;

import static us.connex.miniprojectapt.Model.Constant.BASE_URL;
import static us.connex.miniprojectapt.Model.Constant.EARTH_RADIUS;
import static us.connex.miniprojectapt.Model.Constant.EXTRA_MESSAGE;

/**
 * Created by dranderson on 10/24/17.
 */

public class ShowNearbyActivity extends AppCompatActivity {

    private GridView gridview;
    private ImageGpsAdaptor imageAdapter;
    private List<ImageWithGps> images;
    private int seeMoreClicked = 0;
    private StreamService myStreamService;
    private int streamNumber = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_nearby_stream);
        images = new ArrayList<>();
        ActivityCompat.requestPermissions(ShowNearbyActivity.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);

        getAllImages();

        gridview = (GridView) findViewById(R.id.gridview);
        imageAdapter = new ImageGpsAdaptor(this, images, seeMoreClicked);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String streamName = images.get(position).getStreamName();
                myStreamService = getStreamService();
                getSingleStream(streamName);
            }
        });

        seeMoreClicked++;

        ActivityCompat.requestPermissions(ShowNearbyActivity.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);
        GPStracker gpStracker = new GPStracker(getApplicationContext());
        Location location = gpStracker.getLocation();
        if(location != null) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            Toast.makeText(getApplicationContext(), "LAT: "+lat+"\nLON: "+lon, Toast.LENGTH_LONG).show();
        }
    }

    private void getSingleStream(final String string) {
        myStreamService.getSingleStream_Obj(string).enqueue(new Callback<ViewSingle>() {
            @Override
            public void onResponse(Call<ViewSingle> call, Response<ViewSingle> response) {
                ViewSingle viewsingle = response.body();
                ArrayList<String> photoUrls = viewsingle.getPhotoUrls();
                Intent intent = new Intent(ShowNearbyActivity.this, ShowSingleStreamActivity.class);
                intent.putExtra(EXTRA_MESSAGE, string);
                intent.putStringArrayListExtra("photo_urls", photoUrls);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ViewSingle> call, Throwable t) {

            }
        });
    }

    public void getAllImages() {
        myStreamService = getStreamService();
        getStreamData();
    }

    private void getStreamData() {
        GPStracker gpstracker = new GPStracker(getApplicationContext());
        Location location = gpstracker.getLocation();
        double latLocal = 0;
        double lonLocal = 0;
        if (location != null) {
            latLocal = location.getLatitude();
            lonLocal = location.getLongitude();
        }
        else {
            Toast.makeText(ShowNearbyActivity.this, "GPS not available.\nLocation set to (0,0).",Toast.LENGTH_LONG).show();
        }
        ViewNearby viewNearby = new ViewNearby(1, latLocal, lonLocal);
        myStreamService.getNearbyData_Obj(latLocal, lonLocal, 1).enqueue(new Callback<ViewNearby>() {
            @Override
            public void onResponse(Call<ViewNearby> call, Response<ViewNearby> response) {
                ViewNearby viewNearby = response.body();
                if(viewNearby == null)
                    return;
                for(int i = 0;i < viewNearby.getPhotos().size();i++) {
                    double distance = viewNearby.getPhotos().get(i).getDistance()/1000;
                    DecimalFormat df = new DecimalFormat("0.00");
                    String distance_str = df.format(distance);
                    images.add(new ImageWithGps(viewNearby.getPhotos().get(i).getPhotoUrl(), viewNearby.getPhotos().get(i).getStreamName(),
                            distance_str));
                }
                Collections.sort(images, new Comparator<ImageWithGps>() {
                    @Override
                    public int compare(ImageWithGps imageWithGps, ImageWithGps t1) {
                        return imageWithGps.getDoubleDistance().compareTo(t1.getDoubleDistance());
                    }
                });
                gridview.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<ViewNearby> call, Throwable t) {

            }
        });
    }

    public void seeMoreImages(View view) {
        imageAdapter = new ImageGpsAdaptor(this, images, seeMoreClicked);
        gridview.setAdapter(imageAdapter);
        if(images.size() > 16 && seeMoreClicked < (images.size() - 1) / 16) {
            seeMoreClicked++;
        }
    }

    public void seeStreams(View view) {
        Intent intent = new Intent(this, ShowStreamsActivity.class);
        startActivity(intent);
    }

    public static StreamService getStreamService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }
}
