package com.msh.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Set extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set);
    }

    public void AddSet1(View view)
    {
        Intent intent = new Intent(Set.this,AddQuestion.class);
        intent.putExtra("sets","Set1");
        startActivity(intent);

    }

    public void AddSet2(View view)
    {
        Intent intent = new Intent(Set.this,AddQuestion.class);
        intent.putExtra("sets","Set2");
        startActivity(intent);

    }

    public void AddSet3(View view)
    {
        Intent intent = new Intent(Set.this,AddQuestion.class);
        intent.putExtra("sets","Set3");
        startActivity(intent);

    }

    public void AddSet4(View view)
    {
        Intent intent = new Intent(Set.this,AddQuestion.class);
        intent.putExtra("sets","Set4");
        startActivity(intent);

    }

    public void AddSet5(View view)
    {
        Intent intent = new Intent(Set.this,AddQuestion.class);
        intent.putExtra("sets","Set5");
        startActivity(intent);
    }
}
