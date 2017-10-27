package us.connex.miniprojectapt.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.connex.miniprojectapt.Model.ByteMessage;
import us.connex.miniprojectapt.Model.UploadPhoto;
import us.connex.miniprojectapt.Model.UploadSession;
import us.connex.miniprojectapt.R;
import us.connex.miniprojectapt.Remote.RetrofitClient;
import us.connex.miniprojectapt.Remote.StreamService;

import static us.connex.miniprojectapt.Model.Constant.BASE_URL;
import static us.connex.miniprojectapt.Model.Constant.EXTRA_MESSAGE;


public class ImageUploadActivity extends AppCompatActivity
{

    private static final int PERMISSION_ACCESS_COURSE_LOCATION_REQUEST_CODE = 99;
    private static final int GET_FROM_GALLERY_PERMISSION_REQUEST = 98;
    private static final int TAKE_PHOTO_PERMISSION_REQUEST = 97;
    private static final String LOCATION_LOG_TAG = "location";
    private static final String PHOTO_LOG_TAG = "photo";

    private String streamName;
    private byte[] jpgBytes;
    private String uploadPhotoUrl;
    private StreamService myStreamService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupLayout();
    }

    public void choosePhotoAndUpload(View view) {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI),
            GET_FROM_GALLERY_PERMISSION_REQUEST);
    }
    public void takePhotoAndUpload(View view) {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, TAKE_PHOTO_PERMISSION_REQUEST);
    }

    private void setupLayout() {
        setContentView(R.layout.image_upload);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        streamName = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Stream: " + streamName);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GET_FROM_GALLERY_PERMISSION_REQUEST
            && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                uploadPhoto(bitmap);

            } catch (FileNotFoundException e) {
                Log.e(PHOTO_LOG_TAG, "could not find the file after picking!");
            } catch (IOException e) {
                Log.e(PHOTO_LOG_TAG, "could not read the file after picking!");
            }
        }
        if (requestCode == TAKE_PHOTO_PERMISSION_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            uploadPhoto(bitmap);
        }
    }

    private void setUploadButtonsEnablity(boolean enable)
    {
        findViewById(R.id.button5).setEnabled(enable);
        findViewById(R.id.button6).setEnabled(enable);
    }

    private void uploadPhoto(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        jpgBytes = stream.toByteArray();

        setUploadButtonsEnablity(false);

        myStreamService = getStreamService();
        myStreamService.uploadPhotoSession_Obj().enqueue(new Callback<UploadSession>() {
            @Override
            public void onResponse(Call<UploadSession> call, Response<UploadSession> response) {

                uploadPhotoUrl = response.body().getNewUploadUrl();
                Call<UploadPhoto> call2 =  myStreamService.uploadPhoto_Obj(uploadPhotoUrl,
                    new ByteMessage(jpgBytes),
                    streamName,
                    null, null );
                call2.enqueue(new Callback<UploadPhoto>() {

                    @Override
                    public void onResponse(Call<UploadPhoto> call, Response<UploadPhoto> response) {

                        setUploadButtonsEnablity(true);
                        Toast.makeText(getApplicationContext(),"photo uploaded!",
                                Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<UploadPhoto> call, Throwable t) {
                        setUploadButtonsEnablity(true);
                        Toast.makeText(getApplicationContext(),"could not upload the photo",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<UploadSession> call, Throwable t) {
                Log.e("upload", t.getMessage());
                Toast.makeText(getApplicationContext(),"Could not connect to upload api",
                    Toast.LENGTH_LONG).show();
                setUploadButtonsEnablity(true);
            }
        });
    }

    public static StreamService getStreamService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }
}
