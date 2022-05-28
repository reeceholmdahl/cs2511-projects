package com.example.myfirstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfirstapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button farmerBtn = (Button) findViewById(R.id.farmerButton);
        Button puzzleBtn = (Button) findViewById(R.id.puzzleButton);
    }

    public void farmerButtonPress(View view) {
        Intent intent = new Intent(this, FarmerProblemIntro.class);
        startActivity(intent);
    }

    public void puzzleButtonPress(View view) {
        Intent intent = new Intent(this, PuzzleProblemIntro.class);
        startActivity(intent);
    }

    public void puzzle15ButtonPress(View view) {
        Intent intent = new Intent(this, Puzzle15ProblemIntro.class);
        startActivity(intent);
    }
}