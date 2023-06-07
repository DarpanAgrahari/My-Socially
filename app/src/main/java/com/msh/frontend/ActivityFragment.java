package com.msh.frontend;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityFragment extends Fragment {

    private Button myButton1,myButton2,myButton3,myButton4,myButton5;
    private ProgressBar progressBar1,progressBar2, progressBar3, progressBar4, progressBar5;

    TextView textView1,textView2,textView3,textView4;
    private DatabaseReference databaseReference;
    private String currentUser;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_activity_home, container, false);
        progressBar1=view.findViewById(R.id.progressBar1);
        progressBar2=view.findViewById(R.id.progressBar2);
        progressBar3=view.findViewById(R.id.progressBar3);
        progressBar4=view.findViewById(R.id.progressBar4);
        progressBar5=view.findViewById(R.id.progressBar5);
        textView2=view.findViewById(R.id.progressBar2Text);
        textView1=view.findViewById(R.id.Text1);
        textView3=view.findViewById(R.id.progressBar3Text);
        textView4=view.findViewById(R.id.progressBar4Text);


        // Initialize Firebase database reference and current user
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
                            textView1.setText(String.valueOf(res));
                            progressBar1.setProgress(res);
                        }
                        else if (snapshot.getKey().toString().equals("Set2")){
                            String total= snapshot.getValue().toString();
                            int temp= Integer.valueOf(total) ;
                            int res= (temp*100)/40;
                            textView2.setText(String.valueOf(res));
                            System.out.println(res);
                            progressBar2.setProgress(res);
                        }
                       else if (snapshot.getKey().toString().equals("Set3")){
                            String total= snapshot.getValue().toString();
                            int temp= Integer.valueOf(total) ;
                            int res= (temp*100)/40;
                            textView3.setText(String.valueOf(res));
                            System.out.println(res);
                            progressBar3.setProgress(res);
                        }
                        else if (snapshot.getKey().toString().equals("Set4")){
                            String total= snapshot.getValue().toString();
                            int temp= Integer.valueOf(total) ;
                            int res= (temp*100)/40;
                            textView4.setText(String.valueOf(res));
                            System.out.println(res);
                            progressBar4.setProgress(res);
                        }

                    }
                    progressBar5.setProgress(0,true);
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


        myButton1 = view.findViewById(R.id.activity1btn);

        myButton1.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity1=new Intent (ActivityFragment.this.getActivity(),QuestionActivity.class);
            activity1.putExtra("sets","Set1");
            startActivity(activity1);

        });
        myButton2 = view.findViewById(R.id.activity2btn);

        myButton2.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity2=new Intent (ActivityFragment.this.getActivity(),QuestionActivity.class);
            activity2.putExtra("sets","Set2");
            startActivity(activity2);
        });
        myButton3 = view.findViewById(R.id.activity3btn);

        myButton3.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity3=new Intent (ActivityFragment.this.getActivity(),QuestionActivity.class);
            activity3.putExtra("sets","Set3");
            startActivity(activity3);
        });
        myButton4 = view.findViewById(R.id.activity4btn);

        myButton4.setOnClickListener(v -> {
            // Write the code that should be executed when the Button is clicked
            Intent activity4=new Intent (ActivityFragment.this.getActivity(),QuestionActivity.class);
            activity4.putExtra("sets","Set4");
            startActivity(activity4);
        });

        myButton5= view.findViewById(R.id.activity5btn);
        myButton5.setOnClickListener(v->{
            Intent activity5= new Intent (ActivityFragment.this.getActivity(),QuestionActivity1.class);
            activity5.putExtra("sets","Set5");
            startActivity(activity5);

        });

        return view;
    }


}
