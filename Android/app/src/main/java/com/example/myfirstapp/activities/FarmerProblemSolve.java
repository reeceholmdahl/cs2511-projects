package com.example.myfirstapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myfirstapp.AndroidProblemSolver;
import com.example.myfirstapp.R;
import com.example.myfirstapp.domains.farmer.FarmerProblem;

import java.util.HashMap;

public class FarmerProblemSolve extends AndroidProblemSolver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farmer_problem_solve);

        FarmerProblem problem = new FarmerProblem();
        TextView currentState = (TextView) findViewById(R.id.farmerCurrentState);
        TextView goalState = (TextView) findViewById(R.id.farmerGoalState);
        TextView moveCount = (TextView) findViewById(R.id.farmerMoveCount);
        TextView metaInfo = (TextView) findViewById(R.id.farmerMetaInfo);
        Button resetButton = (Button) findViewById(R.id.farmerResetButton);
        Button solveButton = (Button) findViewById(R.id.farmerSolveButton);
        Button nextButton = (Button) findViewById(R.id.farmerNextButton);
        TextView stats = (TextView) findViewById(R.id.farmerStats);
        HashMap<String, Button> moveButtons = new HashMap<>();
        moveButtons.put("Go Alone", (Button) findViewById(R.id.farmerGoesAlone));
        moveButtons.put("Take Wolf", (Button) findViewById(R.id.farmerTakesWolf));
        moveButtons.put("Take Goat", (Button) findViewById(R.id.farmerTakesGoat));
        moveButtons.put("Take Cabbage", (Button) findViewById(R.id.farmerTakesCabbage));

        init(problem, currentState, goalState, moveCount, moveButtons, metaInfo, resetButton, solveButton, nextButton, null, stats, getColor(R.color.farmer_btn));
    }

    public void goesAlone(View view) {
        moveButtonAction("Go Alone");
    }

    public void takesWolf(View view) {
        moveButtonAction("Take Wolf");
    }

    public void takesGoat(View view) {
        moveButtonAction("Take Goat");
    }

    public void takesCabbage(View view) {
        moveButtonAction("Take Cabbage");
    }
}