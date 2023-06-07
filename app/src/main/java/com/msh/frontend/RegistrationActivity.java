package com.msh.frontend;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser muser;
    EditText inputEmail, inputPassword, inputCurrentPassword;
    Button registerButton;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();

        registerButton = findViewById(R.id.regsignup);
        inputEmail = findViewById(R.id.userEmail);
        inputPassword = findViewById(R.id.upassword);
        inputCurrentPassword = findViewById(R.id.userConfirmPassword);
        progressDialog = new ProgressDialog(this);
        registerButton.setOnClickListener(view ->  {


                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String confirmPassword = inputCurrentPassword.getText().toString();
                if (!email.matches(emailPattern)) {
                    inputEmail.setError("Enter connect Email");
                } else if (password.isEmpty() || password.length() < 6) {
                    inputPassword.setError("Enter proper password");
                } else if (!password.equals(confirmPassword)) {
                    inputCurrentPassword.setError("password not matched");
                } else {
                    progressDialog.setMessage("Please Wait While Registration....");
                    progressDialog.setTitle("Registration");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(RegistrationActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        Intent sintent = new Intent(RegistrationActivity.this, userDetail.class);

                                        startActivity(sintent);
                                    } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(RegistrationActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                            });
                }

        });

    }
}
