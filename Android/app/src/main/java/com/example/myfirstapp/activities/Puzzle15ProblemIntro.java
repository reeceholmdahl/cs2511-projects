package com.example.myfirstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myfirstapp.R;

public class Puzzle15ProblemIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle15_problem);
    }

    public void beginProblem(View view) {
        Intent intent = new Intent(this, Puzzle15ProblemSolve.class);
        startActivity(intent);
    }
}