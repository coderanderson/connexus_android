package us.connex.miniprojectapt.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import us.connex.miniprojectapt.R;

import static us.connex.miniprojectapt.Model.Constant.EXTRA_MESSAGE;

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
        streamName = intent.getStringExtra(EXTRA_MESSAGE);
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("Stream: " + streamName);
    }
}
