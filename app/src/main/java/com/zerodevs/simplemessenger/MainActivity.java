package com.zerodevs.simplemessenger;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    private TheApi TheApi;
    TextView restxt;
    public boolean signupMode = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button postbtn = findViewById(R.id.postbtn);
        TextView restxt = findViewById(R.id.restxt);
        TextView logintxt = findViewById(R.id.logintxt);
        TextView info = findViewById(R.id.infotxt);
        EditText usernamebox = findViewById(R.id.usernamebox);
        EditText emailbox = findViewById(R.id.emailbox);
        EditText passbox = findViewById(R.id.passbox);


        logintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!signupMode) {
                    info.setText("already have account ?");
                    logintxt.setText("login");
                    usernamebox.setVisibility(View.VISIBLE);
                    signupMode = true;
                    usernamebox.setText("");
                    emailbox.setText("");
                    passbox.setText("");
                    postbtn.setText("Signup");

                } else {
                    info.setText("don't have an account ?");
                    logintxt.setText("signup");
                    usernamebox.setVisibility(View.INVISIBLE);
                    signupMode = false;
                    usernamebox.setText("");
                    emailbox.setText("");
                    passbox.setText("");
                    postbtn.setText("Login");
                }

            }
        });


        RetrofitBuilder("https://dl.dropboxusercontent.com");
        GetLink();


        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signupMode) {
                            // the local server ip
                         RetrofitBuilder("http://localhost");
                             Post();
                } else {
                        RetrofitBuilder("http://localhost");
                            login();
                }
            }
        });




    }
    private void RetrofitBuilder(String link) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(link)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        TheApi = retrofit.create(TheApi.class);

    }
    private void Post() {
        TextView restxt = findViewById(R.id.restxt);
        EditText usernamebox = findViewById(R.id.usernamebox);
        EditText emailbox = findViewById(R.id.emailbox);
        EditText passbox = findViewById(R.id.passbox);

        Post post = new Post(String.valueOf(usernamebox.getText()),String.valueOf(emailbox.getText()),String.valueOf(passbox.getText()));
        Call<Post> call = TheApi.createPost(post);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    restxt.setText("error code : " + response.code());
                    return;
                }
                Post postRes = response.body();
                restxt.setText("res code: " + postRes.getResponse());

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                restxt.setText("error"+ t.toString());

            }
        });

    }

    private void login() {
        TextView restxt = findViewById(R.id.restxt);
        EditText usernamebox = findViewById(R.id.usernamebox);
        EditText emailbox = findViewById(R.id.emailbox);
        EditText passbox = findViewById(R.id.passbox);

        Login login = new Login(String.valueOf(emailbox.getText()),String.valueOf(passbox.getText()));
        Call<Post> loginCall = TheApi.createPost(login);
        loginCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (!response.isSuccessful()) {
                    restxt.setText("error code : " + response.code());
                    return;
                }
                Post postRes = response.body();
                restxt.setText("res code: " + postRes.getResponse());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                restxt.setText("error"+ t.toString());
            }
        });


    }

    private void GetLink() {
        TextView restxt = findViewById(R.id.restxt);
        //this will get the new ngrok link
        Call<MyLink> call = TheApi.GetLink();
        call.enqueue(new Callback<MyLink>() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onResponse(Call<MyLink> call, Response<MyLink> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"error : " + response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                        // here is the problem
                        // it shows the app's package name for me instead of response !
                        
                        restxt.setText("res : " + response.body());

            }

            @Override
            public void onFailure(Call<MyLink> call, Throwable t) {
                restxt.setText("error" + t.toString());
            }
        });

    }

}