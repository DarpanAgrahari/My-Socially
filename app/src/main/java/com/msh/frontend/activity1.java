package com.msh.frontend;




import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class activity1 extends AppCompatActivity {

    private Button logoutButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        logoutButton = findViewById(R.id.logOut);
        mAuth = FirebaseAuth.getInstance();
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity1.this);
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked Yes button, handle logout
                        mAuth.signOut();
                        Intent intent = new Intent(activity1.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked No button, dismiss the dialog
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    public void AddQuestion(View view)
    {
        Intent intent = new Intent(activity1.this,Set.class);
        startActivity(intent);

    }

    public void ManageUser(View view){
        Intent intent = new Intent(activity1.this, activity3.class);
        startActivity(intent);
    }
}