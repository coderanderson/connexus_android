package us.connex.miniprojectapt.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import us.connex.miniprojectapt.Model.ViewAll;
import us.connex.miniprojectapt.R;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import us.connex.miniprojectapt.Remote.RetrofitClient;
import us.connex.miniprojectapt.Remote.StreamService;

import static us.connex.miniprojectapt.Model.Constant.BASE_URL;
import static us.connex.miniprojectapt.Model.Constant.SIGN_IN_CODE;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient googleApiClient;
    private SignInButton signInButton;
    private Button viewStreams;
    StreamService myStreamService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setColorScheme(SignInButton.COLOR_DARK);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN_CODE);
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            goMainScreen();
        } else {
            Toast.makeText(this, R.string.not_log_in, Toast.LENGTH_SHORT).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //Call when click viewStreams button
    public void sendMessage(View view) {
        viewStreams = (Button) findViewById(R.id.viewStreams);
        myStreamService = getStreamService();
        getStream();
    }

    private void getStream() {
        myStreamService.getViewAll_Obj().enqueue(new Callback<List<ViewAll>>() {
            @Override
            public void onResponse(Call<List<ViewAll>> call, Response<List<ViewAll>> response) {
                List<ViewAll> list = response.body();
                ArrayList<String> nameList = new ArrayList<>();
                ArrayList<String> coverUrlList = new ArrayList<>();
                for(int i = 0;i < list.size();i++) {
                    nameList.add(list.get(i).getName());
                    coverUrlList.add(list.get(i).getCoverUrl());
                }
                Intent intent = new Intent(MainActivity.this, ShowStreamsActivity.class);
                intent.putStringArrayListExtra("name_list", nameList);
                intent.putStringArrayListExtra("cover_url_list", coverUrlList);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<ViewAll>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    public static StreamService getStreamService() {
        return RetrofitClient.getClient(BASE_URL).create(StreamService.class);
    }
}
