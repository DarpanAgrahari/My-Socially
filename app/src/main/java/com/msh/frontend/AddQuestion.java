package com.msh.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;
import java.util.prefs.AbstractPreferences;

public class AddQuestion extends AppCompatActivity {

    TextInputLayout textInputLayout1, textInputLayout2, textInputLayout3, textInputLayout4, textInputLayout5;
    private String question, optionA, optionB, optionC, optionD,set, randomStr;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addquestion);
        textInputLayout1=findViewById(R.id.question);
        textInputLayout2=findViewById(R.id.optionA);
        textInputLayout3=findViewById(R.id.optionB);
        textInputLayout4=findViewById(R.id.optionC);
        textInputLayout5=findViewById(R.id.optionD);

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("Question");

        set=getIntent().getStringExtra("sets");
         randomStr = usingRandomUUID();




    }

    static String usingRandomUUID() {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replaceAll("_", "");

    }

    public void AddQuestion(View view)
    {

        question = textInputLayout1.getEditText().getText().toString();
        optionA = textInputLayout2.getEditText().getText().toString();
        optionB = textInputLayout3.getEditText().getText().toString();
        optionC = textInputLayout4.getEditText().getText().toString();
        optionD = textInputLayout5.getEditText().getText().toString();


        question_getter_setter helperClass = new question_getter_setter(question,optionA,optionB,optionC,optionD);
        reference.child(set).child(randomStr).setValue(helperClass);
        Toast.makeText(AddQuestion.this, "Question Added Successfully", Toast.LENGTH_SHORT).show();
        Intent intent4 = new Intent(AddQuestion.this,activity1.class);

        startActivity(intent4);
    }


}
