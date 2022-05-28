package com.example.myfirstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myfirstapp.R;

public class PuzzleProblemIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_problem);
    }

    public void beginProblem(View view) {
        Intent intent = new Intent(this, PuzzleProblemSolve.class);
        startActivity(intent);
    }
}