package com.msh.frontend;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class contactus extends AppCompatActivity {
    Button btnApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contanctus);


        btnApi = findViewById(R.id.buttonAPi);




        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Request request;
                // we add the information we want to send in
                // a form. each string we want to send should
                // have a name. in our case we sent the
                // dummyText with a name 'sample'
                OkHttpClient client = new OkHttpClient().newBuilder()
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS).build();
                RequestBody formBody = new FormBody.Builder()

                        .add("Age", "22")
                        .add("Friends", "15")
                        .add("Outing", "5")
                        .add("Comm", "5")
                        .add("Talk", "4")
                        .add("Finance", "4")
                        .add("Lone", "1")
                        .add("Stable", "1")
                        .add("Depression", "1")
                        .add("Gender", "1")
                        .build();


                request=new Request.Builder()
                        .url("https://mlmodeldemo.onrender.com/predict")
                        .post(formBody)
                        .build();


                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(
                            @NotNull Call call,
                            @NotNull IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "server down", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String responseData = response.body().string();
                        System.out.println(responseData);
                        if (response!=null) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //System.out.println(response.body().string());
                                    Toast.makeText(getApplicationContext(), "data received", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });
    }
}


