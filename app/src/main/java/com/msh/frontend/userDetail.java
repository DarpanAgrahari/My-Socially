package com.msh.frontend;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.msh.frontend.databinding.UserdetailBinding;




public class userDetail extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextPhoneNumber, editTextEmail;
    private Button buttonSubmit;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdetail);

        // Initialize views
        editTextFirstName = findViewById(R.id.first_name);
        editTextLastName = findViewById(R.id.last_name);
        editTextPhoneNumber = findViewById(R.id.phone_number);
        editTextEmail = findViewById(R.id.userEmail);
        buttonSubmit = findViewById(R.id.register_button);

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        FirebaseAuth auth=FirebaseAuth.getInstance();
        String uid=auth.getCurrentUser().getUid();



        // Set click listener for submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();

                // Create new user object
                Users user = new Users(firstName, lastName, phoneNumber, email);

                // Save user object to Firebase database
                databaseReference.child(uid).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        editTextFirstName.setText("");
                        editTextLastName.setText("");
                        editTextPhoneNumber.setText("");
                        editTextEmail.setText("");


                        Intent intent = new Intent(userDetail.this, FinalActivity.class);
                        startActivity(intent);
                        Toast.makeText(userDetail.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        finish();



                    }
                });
            }
        });
    }
}

