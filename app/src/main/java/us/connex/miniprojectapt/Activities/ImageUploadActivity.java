package us.connex.miniprojectapt.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import us.connex.miniprojectapt.R;

import static us.connex.miniprojectapt.Model.Constant.EXTRA_MESSAGE;

/**
 * Created by dranderson on 10/23/17.
 */

public class ImageUploadActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final int PERMISSION_ACCESS_COURSE_LOCATION_REQUEST_CODE = 99;
    private String streamName;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
        setLocationApi();
    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private void setupLayout() {
        setContentView(R.layout.image_upload);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        streamName = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Stream: " + streamName);
    }

    private void setLocationApi() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this, new String[] {  Manifest.permission.ACCESS_COARSE_LOCATION  },
                    PERMISSION_ACCESS_COURSE_LOCATION_REQUEST_CODE );
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if(mLastLocation!=null)
            Log.d("location", "current location: " + mLastLocation.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if(requestCode == PERMISSION_ACCESS_COURSE_LOCATION_REQUEST_CODE)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.i("location", "granted location permission");
                getLocation();
            }

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d("location", "connected to location api");
        getLocation();

    }
    @Override
    public void onConnectionSuspended(int i) { Log.d("location", "disconnected from location api"); }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) { Log.e("location", "could not connect to location api"); }
}
