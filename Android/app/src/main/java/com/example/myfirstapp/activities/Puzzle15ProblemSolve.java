package com.example.myfirstapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myfirstapp.AndroidProblemSolver;
import com.example.myfirstapp.R;
import com.example.myfirstapp.domains.puzzle15.Puzzle15Problem;

import java.util.HashMap;

public class Puzzle15ProblemSolve extends AndroidProblemSolver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle15_problem_solve);

        Puzzle15Problem problem = new Puzzle15Problem();
        TextView currentState = (TextView) findViewById(R.id.puzzle15CurrentState);
        TextView goalState = (TextView) findViewById(R.id.puzzle15GoalState);
        TextView moveCount = (TextView) findViewById(R.id.puzzle15MoveCount);
        Spinner benchmarkChoice = (Spinner) findViewById(R.id.puzzle15BenchmarkChoice);
        TextView metaInfo = (TextView) findViewById(R.id.puzzle15MetaInfo);
        Button resetButton = (Button) findViewById(R.id.puzzle15ResetButton);
        Button solveButton = (Button) findViewById(R.id.puzzle15SolveButton);
        Button nextButton = (Button) findViewById(R.id.puzzle15NextButton);
        TextView stats = (TextView) findViewById(R.id.puzzle15Stats);
        HashMap<String, Button> moveButtons = new HashMap<>();
        moveButtons.put("Slide Tile 1", (Button) findViewById(R.id.puzzle15SlideTile1));
        moveButtons.put("Slide Tile 2", (Button) findViewById(R.id.puzzle15SlideTile2));
        moveButtons.put("Slide Tile 3", (Button) findViewById(R.id.puzzle15SlideTile3));
        moveButtons.put("Slide Tile 4", (Button) findViewById(R.id.puzzle15SlideTile4));
        moveButtons.put("Slide Tile 5", (Button) findViewById(R.id.puzzle15SlideTile5));
        moveButtons.put("Slide Tile 6", (Button) findViewById(R.id.puzzle15SlideTile6));
        moveButtons.put("Slide Tile 7", (Button) findViewById(R.id.puzzle15SlideTile7));
        moveButtons.put("Slide Tile 8", (Button) findViewById(R.id.puzzle15SlideTile8));
        moveButtons.put("Slide Tile 9", (Button) findViewById(R.id.puzzle15SlideTile9));
        moveButtons.put("Slide Tile 10", (Button) findViewById(R.id.puzzle15SlideTile10));
        moveButtons.put("Slide Tile 11", (Button) findViewById(R.id.puzzle15SlideTile11));
        moveButtons.put("Slide Tile 12", (Button) findViewById(R.id.puzzle15SlideTile12));
        moveButtons.put("Slide Tile 13", (Button) findViewById(R.id.puzzle15SlideTile13));
        moveButtons.put("Slide Tile 14", (Button) findViewById(R.id.puzzle15SlideTile14));
        moveButtons.put("Slide Tile 15", (Button) findViewById(R.id.puzzle15SlideTile15));

        init(problem, currentState, goalState, moveCount, moveButtons, metaInfo, resetButton, solveButton, nextButton, benchmarkChoice, stats, getColor(R.color.puzzle15_btn));
    }

    public void slideTile1(View view) {
        moveButtonAction("Slide Tile 1");
    }

    public void slideTile2(View view) {
        moveButtonAction("Slide Tile 2");
    }

    public void slideTile3(View view) {
        moveButtonAction("Slide Tile 3");
    }

    public void slideTile4(View view) {
        moveButtonAction("Slide Tile 4");
    }

    public void slideTile5(View view) {
        moveButtonAction("Slide Tile 5");
    }

    public void slideTile6(View view) {
        moveButtonAction("Slide Tile 6");
    }

    public void slideTile7(View view) {
        moveButtonAction("Slide Tile 7");
    }

    public void slideTile8(View view) {
        moveButtonAction("Slide Tile 8");
    }

    public void slideTile9(View view) {
        moveButtonAction("Slide Tile 9");
    }

    public void slideTile10(View view) {
        moveButtonAction("Slide Tile 10");
    }

    public void slideTile11(View view) {
        moveButtonAction("Slide Tile 11");
    }

    public void slideTile12(View view) {
        moveButtonAction("Slide Tile 12");
    }

    public void slideTile13(View view) {
        moveButtonAction("Slide Tile 13");
    }

    public void slideTile14(View view) {
        moveButtonAction("Slide Tile 14");
    }

    public void slideTile15(View view) {
        moveButtonAction("Slide Tile 15");
    }
}