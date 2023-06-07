package com.msh.frontend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MoreFragment extends Fragment {
    private FirebaseAuth mAuth;
    private Button logoutButton;
   private Button myButton1,myButton2,myButton3,myButton4,myButton5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        mAuth = FirebaseAuth.getInstance();
        // Set up logout button
        logoutButton = view.findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MoreFragment.this.getActivity());
                builder.setTitle("Logout");
                builder.setMessage("Are you sure you want to logout?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User clicked Yes button, handle logout
                        mAuth.signOut();
                        Intent intent = new Intent(MoreFragment.this.getActivity(), LoginActivity.class);
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
        myButton1 = view.findViewById(R.id.button1);

        myButton1.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (MoreFragment.this.getActivity(),setting.class);
            startActivity(activity1);
        });
        myButton2 = view.findViewById(R.id.button2);

        myButton2.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (MoreFragment.this.getActivity(),help.class);
            startActivity(activity1);
        });
        myButton3 = view.findViewById(R.id.button3);

        myButton3.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (MoreFragment.this.getActivity(),aboutus.class);
            startActivity(activity1);
        });
        myButton4 = view.findViewById(R.id.button4);

        myButton4.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (MoreFragment.this.getActivity(),terms.class);
            startActivity(activity1);
        });
        myButton5 = view.findViewById(R.id.button5);

        myButton5.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (MoreFragment.this.getActivity(),contactus.class);
            startActivity(activity1);
        });




        return view;

    }
}

