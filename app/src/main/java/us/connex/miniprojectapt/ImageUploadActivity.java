package us.connex.miniprojectapt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by dranderson on 10/23/17.
 */

public class ImageUploadActivity extends AppCompatActivity {

    private String streamName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_upload);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        streamName = intent.getStringExtra(ShowSingleStreamActivity.EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Stream: " + streamName);
    }

    public void openCamera(View view) {
        Intent intent = new Intent(this, cameraViewActivity.class);
        startActivity(intent);
    }
}
