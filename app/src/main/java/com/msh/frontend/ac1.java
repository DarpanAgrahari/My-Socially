package com.msh.frontend;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
import android.widget.Button;
import android.content.Context;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class ac1 extends AppCompatActivity {
    Button btnSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ac1);


        btnSplash = findViewById(R.id.button);
        btnSplash.setOnClickListener(view -> {
            Intent intent = new Intent(ac1.this, LoginActivity.class);
            startActivity(intent);

        });
    }
}
