package com.msh.frontend;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {
    TextView register;
    FirebaseAuth mAuth;
    FirebaseUser muser;
    EditText inputEmail, inputPassword;
    Button loginButton;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        register=findViewById(R.id.register);
        register.setOnClickListener(view -> {
            Intent loginent=new Intent(LoginActivity.this,RegistrationActivity.class);
            startActivity(loginent);

        });
        inputEmail =findViewById(R.id.loginEmail);
        inputPassword =findViewById(R.id.loginPassword);
        loginButton=findViewById(R.id.LOGIN);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);
        if (muser != null) {
            // User is already logged in, go to MainActivity
            startActivity(new Intent(LoginActivity.this, FinalActivity.class));
            finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (!email.matches(emailPattern)) {
                    inputEmail.setError("Enter connect Email");
                } else if (password.isEmpty() || password.length() < 6) {
                    inputPassword.setError("Enter proper password");
                } else {
                    progressDialog.setMessage("Please Wait While Login....");
                    progressDialog.setTitle("Login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                if(email.equals("darpanagrahari@gmail.com")){
                                    Intent intent = new Intent(LoginActivity.this,activity1.class);
                                    startActivity(intent);
                                }else{
                                Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent sintent = new Intent(LoginActivity.this, FinalActivity.class);

                                startActivity(sintent);}
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "loginfailed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit the app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity(); // Close all running activities
                        System.exit(0);  // Kill the app process
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}

