package com.example.myfirstapp;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfirstapp.framework.problem.Benchmark;
import com.example.myfirstapp.framework.problem.Problem;
import com.example.myfirstapp.framework.problem.State;
import com.example.myfirstapp.framework.solution.AStarSolver;
import com.example.myfirstapp.framework.solution.Solution;
import com.example.myfirstapp.framework.solution.SolvingAssistant;

import java.util.HashMap;

public abstract class AndroidProblemSolver extends AppCompatActivity {

    private SolvingAssistant solver;
    private Problem problem;

    private TextView currentState;
    private TextView goalState;
    private TextView moveCount;
    private HashMap<String, Button> moveButtons;

    private TextView metaInfo;

    private Button resetButton;
    private Button solveButton;
    private Button nextButton;
    private Spinner benchmarkChoice;
    private TextView statistics;

    private AStarSolver autoSolver;
    private Solution solution;

    private int originalColor;

    public void init(Problem problem,
                                TextView currentState,
                                TextView goalState,
                                TextView moveCount,
                                HashMap<String, Button> moveButtons,
                                TextView metaInfo,
                                Button resetButton,
                                Button solveButton,
                                Button nextButton,
                                Spinner benchmarkChoice,
                                TextView statistics,
                                int originalColor
    ) {
        solver = new SolvingAssistant(problem);
        this.problem = problem;
        this.currentState = currentState;
        this.goalState = goalState;
        this.moveCount = moveCount;
        this.metaInfo = metaInfo;
        this.benchmarkChoice = benchmarkChoice; // can be null
        this.statistics = statistics;

        this.resetButton = resetButton;
        this.solveButton = solveButton;
        this.nextButton = nextButton;
        this.moveButtons = moveButtons;

        this.originalColor = originalColor;

        if (benchmarkChoice != null) {
            benchmarkChoice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String selection = (String) benchmarkChoice.getItemAtPosition(position);

                    Benchmark benchmark = problem.getBenchmarks().stream().filter(b -> b.getName().equals(selection)).findFirst().get();
                    problem.setInitialState(benchmark.getStart());
                    problem.setFinalState(benchmark.getGoal());
                    solver.reset();
                    updateStateDisplay();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    return;
                }
            });
        }

        updateStateDisplay();
    }

    private void updateStateDisplay() {
        currentState.setText(problem.getCurrentState().toString());
        goalState.setText(problem.getFinalState().toString());
        moveCount.setText(String.valueOf(solver.getMoveCount()));
    }

    public void moveButtonAction(String moveName) {
        if (!problem.getMover().getMoveNames().contains(moveName)) {
            throw new RuntimeException("The " + problem.getName() + " problem does not have the move: " + moveName + ".");
        }

        if (!solver.isProblemSolved()) {

            solver.tryMove(moveName);

            updateStateDisplay();

            if (!solver.isMoveLegal()) {
                metaInfo.setText("Invalid move, try again");
                metaInfo.setTextColor(getColor(R.color.issue));
            } else {
                metaInfo.setText("");
            }

            if (solver.isProblemSolved()) {
                metaInfo.setText("Problem finished in " + solver.getMoveCount() + " moves!");
                metaInfo.setTextColor(getColor(R.color.success));
                currentState.setBackgroundResource(R.drawable.final_state);
            }
        }
    }

    public void solve(View view) {
        solveButton.setEnabled(false);
        nextButton.setEnabled(true);
        moveButtons.forEach((s, b) -> b.setEnabled(false));

        if (benchmarkChoice != null) {
            benchmarkChoice.setEnabled(false);
        }

        autoSolver = new AStarSolver(problem);
        autoSolver.solve();
        solution = autoSolver.getSolution();
        statistics.setText(autoSolver.getStatistics().toString());

        problem.setCurrentState((State) solution.next().getData());
        updateStateDisplay();
    }

    public void reset(View view) {
        solver.reset();
        metaInfo.setText("");
        updateStateDisplay();
        moveButtons.forEach((s, b) -> {
            b.setEnabled(true);
            b.setBackgroundColor(originalColor);
        });
        currentState.setBackgroundResource(R.drawable.initial_state);
        solveButton.setEnabled(true);
        nextButton.setEnabled(false);
        if (benchmarkChoice != null) {
            benchmarkChoice.setEnabled(true);
        }
    }

    public void next(View view) {

        if (solution.hasNext()) {
            State nextState = (State) solution.next().getData();
            solver.update(nextState);
            updateStateDisplay();

            moveButtons.forEach((s, b) -> b.setBackgroundColor(getColor(R.color.moves_gray)));
            moveButtons.get(nextState.getMove()).setBackgroundColor(getColor(R.color.success));

            if (!solution.hasNext()) {
                nextButton.setEnabled(false);
                metaInfo.setText("Problem solved with help");
                metaInfo.setTextColor(getColor(R.color.meta));
                currentState.setBackgroundResource(R.drawable.final_state);
            }
        }
    }
}
