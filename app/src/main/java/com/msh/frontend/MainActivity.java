package com.msh.frontend;


import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.view.Window;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {


    //Button btnSplash;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                Intent i = new Intent(MainActivity.this, ac1.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();}
            }
        }, 2000);

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_splash);

        if (isNetworkAvailable()) {
            // Internet is available, do your task here
            Toast.makeText(this, "Welcome to Socially", Toast.LENGTH_LONG);
        } else {
            // Internet is not available, show a message
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }
        /*btnSplash=findViewById(R.id.button);
        btnSplash.setOnClickListener(view -> {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);

        });*/
    }
    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

}