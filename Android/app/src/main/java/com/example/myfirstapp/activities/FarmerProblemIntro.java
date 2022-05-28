package com.example.myfirstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

import com.example.myfirstapp.R;

public class FarmerProblemIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_problem);

        ge

        View test = findViewById();
        test.add


    }

    public void beginProblem(View view) {
        Intent intent = new Intent(this, FarmerProblemSolve.class);
        startActivity(intent);
    }
}