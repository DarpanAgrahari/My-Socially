package com.msh.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    private ProgressBar progressBar;
    TextView textView;
    DatabaseReference databaseReference;
    private String currentUser;

    private String set;
    private Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
       // progressBar=findViewById(R.id.ResultProgressBar);
        textView=findViewById(R.id.TextProgressBar);
        button1= findViewById(R.id.button);
        button2=findViewById(R.id.button1);

        set=getIntent().getStringExtra("sets");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Response");
        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child(currentUser).child(set).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    /*String total= dataSnapshot.getValue().toString();
                    int temp= Integer.valueOf(total) ;
                    int res= (temp*100)/40;
                    System.out.println(res);
                    textView.setText(String.valueOf(res));
                    progressBar.setProgress(res);*/

                    }

                 else {
                    Log.e("HOME FRAGMENT", "data snapshot does not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        }
        );
        button1.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (Result.this,QuestionActivity.class);
            activity1.putExtra("sets",set);
            startActivity(activity1);

        });
        button2.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (Result.this,HomeFragment.class);
            startActivity(activity1);

        });
        }


    }
