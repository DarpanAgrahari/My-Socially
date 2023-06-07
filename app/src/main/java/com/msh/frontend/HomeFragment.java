
package com.msh.frontend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment {

    private TextView textViewUsername;

    private DatabaseReference databaseReference;
    private String currentUser;






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        textViewUsername = view.findViewById(R.id.textView2);
        final ProgressBar progressBar1,progressBar2,progressBar3,progressBar4,progressBar5;
        progressBar1=view.findViewById(R.id.progressBar31);
        progressBar2=view.findViewById(R.id.progressBar32);
        progressBar3=view.findViewById(R.id.progressBar33);
        progressBar4=view.findViewById(R.id.progressBar34);
        progressBar5=view.findViewById(R.id.progressBar35);
        progressBar1.setProgress(0,true);
        progressBar2.setMax(100);
        progressBar3.setProgress(0,true);
        progressBar4.setProgress(0,true);
        progressBar5.setProgress(0,true);

        // Initialize Firebase database reference and current user
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("HOME FRAGMENT", "data snapshot:" + dataSnapshot.toString());

                if (dataSnapshot.exists()) {
                    // Get user data
                    String username = "Hello, " + dataSnapshot.child("firstName").getValue(String.class) + "...!";
                    Log.d("HOME FRAGMENT", "Username:" + username);
                    if (textViewUsername != null) {
                        textViewUsername.setText(username);
                    } else {
                        Log.e("HOME FRAGMENT", "one or more textview is null");
                    }

                } else {
                    Log.e("HOME FRAGMENT", "data snapshot does not exist");
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        }
        );

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Response");
        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Activity Fragment", "data snapshot:" + dataSnapshot.toString());


                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        Log.d("Activity Fragment","set"+snapshot.toString());
                        if(snapshot.getKey().toString().equals("Set1")){
                            String total= snapshot.getValue().toString();
                            int temp= Integer.valueOf(total) ;
                            int res= (temp*100)/40;
                            System.out.println(res);
                            progressBar1.setProgress(res);
                        }
                       if (snapshot.getKey().toString().equals("Set2")){
                            String total= snapshot.getValue().toString();
                            int temp= Integer.valueOf(total) ;
                            int res= (temp*100)/40;
                            System.out.println(res);
                            progressBar2.setProgress(res);
                        }
                       else if (snapshot.getKey().toString().equals("Set3")){
                            String total= snapshot.getValue().toString();
                            int temp= Integer.valueOf(total) ;
                            int res= (temp*100)/40;
                            System.out.println(res);
                            progressBar3.setProgress(res);
                        }
                        else if (snapshot.getKey().toString().equals("Set4")){
                            String total= snapshot.getValue().toString();
                            int temp= Integer.valueOf(total) ;
                            int res= (temp*100)/40;
                            System.out.println(res);
                            progressBar4.setProgress(res);
                        }
                   // progressBar5.setProgress(50,true);*/

                    }
                } else {
                    Log.e("HOME FRAGMENT", "data snapshot does not exist");
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        }
        );
        return view;
    }


}