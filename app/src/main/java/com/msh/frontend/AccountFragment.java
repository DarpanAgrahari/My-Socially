package com.msh.frontend;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountFragment extends Fragment {

    private TextView textViewUsername, textViewEmail, textViewPhoneNumber;

    private DatabaseReference databaseReference;
    private String currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // Initialize views
        textViewUsername = view.findViewById(R.id.textView2);
        textViewEmail = view.findViewById(R.id.textView3);
        textViewPhoneNumber = view.findViewById(R.id.textView4);

        // Initialize Firebase database reference and current user
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if (currentUser != null) {
            Log.e("Notification fragment", "user is not logged in"+ currentUser);

        }

        // Retrieve user data from Firebase database
        databaseReference.child(currentUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("NOTFICATION FRAGMENT", "datasnapshot:" + dataSnapshot.toString());

                if (dataSnapshot.exists()) {
                    // Get user data
                    String username = dataSnapshot.child("firstName").getValue(String.class) + " " +
                            dataSnapshot.child("lastName").getValue(String.class);
                    String email = dataSnapshot.child("gmail").getValue(String.class);
                    String phoneNumber = dataSnapshot.child("phoneNumber").getValue(String.class);

                    Log.d("NOTIFICATIONFRAGMENT", "Usename:" + username);
                    Log.d("NOTIFICATIONFRAGMENT", "email:" + email);
                    Log.d("NOTIFICATIONFRAGMENT", "number:" + phoneNumber);
                    if (textViewUsername != null && textViewEmail != null && textViewPhoneNumber != null) { // Update text views with user data
                        textViewUsername.setText(username);
                        textViewEmail.setText(email);
                        textViewPhoneNumber.setText(phoneNumber);
                    } else {
                        Log.e("NOTIFICATIONFRAGMENT", "one or more textview is null");
                    }

                } else {
                    Log.e("NOTIFICATIONFRAGMENT", "datasnapsht does not exist");
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

        return view;
    }
}












