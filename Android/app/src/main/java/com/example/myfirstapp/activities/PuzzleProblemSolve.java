package com.example.myfirstapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myfirstapp.AndroidProblemSolver;
import com.example.myfirstapp.R;
import com.example.myfirstapp.domains.puzzle.PuzzleProblem;

import java.util.HashMap;

public class PuzzleProblemSolve extends AndroidProblemSolver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_problem_solve);

        PuzzleProblem problem = new PuzzleProblem();
        TextView currentState = (TextView) findViewById(R.id.puzzleCurrentState);
        TextView goalState = (TextView) findViewById(R.id.puzzleGoalState);
        TextView moveCount = (TextView) findViewById(R.id.puzzleMoveCount);
        Spinner benchmarkChoice = (Spinner) findViewById(R.id.puzzleBenchmarkChoice);
        TextView metaInfo = (TextView) findViewById(R.id.puzzleMetaInfo);
        Button resetButton = (Button) findViewById(R.id.puzzleResetButton);
        Button solveButton = (Button) findViewById(R.id.puzzleSolveButton);
        Button nextButton = (Button) findViewById(R.id.puzzleNextButton);
        TextView stats = (TextView) findViewById(R.id.puzzleStats);
        HashMap<String, Button> moveButtons = new HashMap<>();
        moveButtons.put("Slide Tile 1", (Button) findViewById(R.id.puzzleSlideTile1));
        moveButtons.put("Slide Tile 2", (Button) findViewById(R.id.puzzleSlideTile2));
        moveButtons.put("Slide Tile 3", (Button) findViewById(R.id.puzzleSlideTile3));
        moveButtons.put("Slide Tile 4", (Button) findViewById(R.id.puzzleSlideTile4));
        moveButtons.put("Slide Tile 5", (Button) findViewById(R.id.puzzleSlideTile5));
        moveButtons.put("Slide Tile 6", (Button) findViewById(R.id.puzzleSlideTile6));
        moveButtons.put("Slide Tile 7", (Button) findViewById(R.id.puzzleSlideTile7));
        moveButtons.put("Slide Tile 8", (Button) findViewById(R.id.puzzleSlideTile8));

        init(problem, currentState, goalState, moveCount, moveButtons, metaInfo, resetButton, solveButton, nextButton, benchmarkChoice, stats, getColor(R.color.puzzle_btn));
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
}