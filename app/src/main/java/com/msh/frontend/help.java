package com.msh.frontend;


import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class help extends AppCompatActivity {
    private TextView textView1, textView2,textView3,textView4,textView5;
    private TextView contentTextView1,contentTextView2,contentTextView3,contentTextView4,contentTextView5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);





        textView1 = findViewById(R.id.button1);
        contentTextView1 = findViewById(R.id.content_text_view1);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentTextView1.getVisibility() == View.GONE) {
                    contentTextView1.setVisibility(View.VISIBLE);
                } else {
                    contentTextView1.setVisibility(View.GONE);
                }
            }
        });

        textView2 = findViewById(R.id.button2);
        contentTextView2 = findViewById(R.id.content_text_view2);

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentTextView2.getVisibility() == View.GONE) {
                    contentTextView2.setVisibility(View.VISIBLE);
                } else {
                    contentTextView2.setVisibility(View.GONE);
                }
            }
        });


        textView3 = findViewById(R.id.button3);
        contentTextView3 = findViewById(R.id.content_text_view3);

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentTextView3.getVisibility() == View.GONE) {
                    contentTextView3.setVisibility(View.VISIBLE);
                } else {
                    contentTextView3.setVisibility(View.GONE);
                }
            }
        });
        textView4 = findViewById(R.id.button4);
        contentTextView4 = findViewById(R.id.content_text_view4);

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentTextView4.getVisibility() == View.GONE) {
                    contentTextView4.setVisibility(View.VISIBLE);
                } else {
                    contentTextView4.setVisibility(View.GONE);
                }
            }
        });
        textView5 = findViewById(R.id.button5);
        contentTextView5 = findViewById(R.id.content_text_view5);

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentTextView5.getVisibility() == View.GONE) {
                    contentTextView5.setVisibility(View.VISIBLE);
                } else {
                    contentTextView5.setVisibility(View.GONE);
                }
            }
        });
    }
}
