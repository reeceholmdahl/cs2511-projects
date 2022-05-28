/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import framework.problem.*;
import framework.solution.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;

/**
 *
 * @author rholm
 */
public class ProblemGUI extends VBox {
    
    // Constants related to the graphical representation of the problem
    private static final int STATE_SIZE = 350;
    private static final int BUTTON_WIDTH = 300;
    private static final int STATS_WIDTH = 300;
    private static final int STATS_HEIGHT = 150;
    private static final Font WELCOME_FONT = Font.font("sans-serif", FontWeight.BOLD, 40);
    private static final Font INTRO_FONT = Font.font("serif", FontWeight.LIGHT, 20);
    private static final Font STATE_LABEL_FONT = Font.font("serif", FontWeight.BOLD, 32);
    private static final Font STATE_FONT = Font.font("monospace", 30);
    private static final Font BUTTON_FONT = Font.font("serif", 24);
    private static final Font META_INFO_FONT = Font.font("serif", FontWeight.MEDIUM, 24);
    private static final Font STATS_FONT = Font.font("monosapce", 12);
    private Solution solution;
    private BFSStateSpaceSolver bfsSolver;
    private DFSStateSpaceSolver dfsSolver;
    private BestFirstSolver bestSolver;
    private AStarSolver aStarSolver;
    
    private Solution getSolution() {
        return solution;
    }
    
    private void setSolution(Solution s) {
        solution = s;
    }
    
    /**
     * Creates a new GUI representing a problem as a JavaFX HBox.
     * 
     * @param problem The problem to graphically represent
     * @param width The width of the window
     * @param height The height of the window
     */
    public ProblemGUI(Problem problem, int width, int height) {
        
        // Solver
        SolvingAssistant solver = new SolvingAssistant(problem);
        
        // Pre-emptive definitions
        Text metaInformationLabel = new Text();
        
        // Create and customize the welcome label
        Label welcome = new Label("Welcome to the " + problem.getName() + " Problem");
        welcome.setFont(WELCOME_FONT);
        welcome.setTextAlignment(TextAlignment.CENTER);
        welcome.setWrapText(true);
        
        // Make a container to properly position the welcome label
        HBox welcomeContainer = new HBox();
        welcomeContainer.getChildren().add(welcome);
        welcomeContainer.setAlignment(Pos.BASELINE_CENTER);
        
        // Create and customize the intro label
        Label intro = new Label(problem.getIntroduction());
        intro.setFont(INTRO_FONT);
        intro.setWrapText(true);
        intro.setTextAlignment(TextAlignment.CENTER);
        welcome.setMaxWidth(1200);
        
        // Make a container to properly position the intro label
        HBox introContainer = new HBox();
        introContainer.getChildren().add(intro);
        introContainer.setAlignment(Pos.BASELINE_CENTER);
        
        // An HBox containing everything that has to do with viewing and manipulating the state
        HBox stateControl = new HBox();
        stateControl.setPadding(new Insets(10, 0, 10, 0));
        
        // A VBox for the current state and its label
        VBox currStateSection = new VBox();
        
        // Create and customize the current state label
        Label currStateLabel = new Label("Current State:");
        currStateLabel.setFont(STATE_LABEL_FONT);
        currStateLabel.setTextAlignment(TextAlignment.CENTER);
        
        // Make a container to properly position the current state label
        HBox currStateLabelContainer = new HBox();
        currStateLabelContainer.setPrefWidth(STATE_SIZE);
        currStateLabelContainer.setAlignment(Pos.CENTER);
        currStateLabelContainer.getChildren().add(currStateLabel);
        
        // Create the textual representation of the current state
        Label currState = new Label(problem.getCurrentState().toString());
        currState.setFont(STATE_FONT);
        
        // Make a container to properly position and style the current state
        HBox currStateContainer = new HBox();
        currStateContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        currStateContainer.setPadding(new Insets(10, 10, 10, 10));
        currStateContainer.setAlignment(Pos.CENTER);
        currStateContainer.setMaxWidth(STATE_SIZE);
        currStateContainer.setPrefHeight(STATE_SIZE);
        currStateContainer.getChildren().add(currState);
        
        // Add all of the parts related to the current state to its container
        currStateSection.getChildren().addAll(currStateLabelContainer, currStateContainer);
        
        // A VBox for the goal state and its label
        VBox goalStateSection = new VBox();
        
        // Create and customize the goal state label
        Label goalStateLabel = new Label("Goal State:");
        goalStateLabel.setFont(STATE_LABEL_FONT);
        goalStateLabel.setTextAlignment(TextAlignment.CENTER);
        
        // Make a container to properly position the goal state label
        HBox goalStateLabelContainer = new HBox();
        goalStateLabelContainer.setPrefWidth(STATE_SIZE);
        goalStateLabelContainer.setAlignment(Pos.CENTER);
        goalStateLabelContainer.getChildren().add(goalStateLabel);
        
        // Create the textual representation of the goal state
        Label goalState = new Label(problem.getFinalState().toString());
        goalState.setFont(STATE_FONT);
        
        // Make a container to properly position and style the goal state
        HBox goalStateContainer = new HBox();
        goalStateContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        goalStateContainer.setPadding(new Insets(10, 10, 10, 10));
        goalStateContainer.setAlignment(Pos.CENTER);
        goalStateContainer.setMaxWidth(STATE_SIZE);
        goalStateContainer.setPrefHeight(STATE_SIZE);
        goalStateContainer.getChildren().add(goalState);
        
        // Add all of the parts related to the goal state to its container
        goalStateSection.getChildren().addAll(goalStateLabelContainer, goalStateContainer);
        
        // Make a container for the move count and all of the possible moves
        VBox stateMoves = new VBox();
        stateMoves.setPadding(new Insets(0, 50, 0, 50));
        stateMoves.setAlignment(Pos.CENTER);
        
        // Create a container to position the move counter
        HBox moveCountContainer = new HBox();
        moveCountContainer.setAlignment(Pos.CENTER);
        moveCountContainer.setPrefWidth(BUTTON_WIDTH);
        
        // Create and style the move count label
        Label moveCount = new Label("Moves (0 so far):");
        moveCount.setFont(STATE_LABEL_FONT);
        moveCount.setTextAlignment(TextAlignment.CENTER);
        moveCountContainer.getChildren().add(moveCount);
        stateMoves.getChildren().add(moveCountContainer);
        
        // Create a button for each possible move, style it, position it, and assign it its proper action
        List<Button> moveButtons = new ArrayList<>();
        problem.getMover().getMoveNames().forEach(move -> {
            
            // Create and style the button
            Button button = new Button(move);
            button.setFont(BUTTON_FONT);
            button.setPrefWidth(BUTTON_WIDTH);
            
            moveButtons.add(button);
            
            // Assign action to the button
            button.setOnAction(e -> {
                
                // Only do the action if the problem is unsolved
                if (!solver.isProblemSolved()) {
                    
                    // Try the move if pressed
                    solver.tryMove(move);
                
                    // Update the state and move count to reflect this move
                    currState.setText(problem.getCurrentState().toString());
                    moveCount.setText("Moves (" + solver.getMoveCount() + " so far):");
                    
                    // if the move is illegal, indicate so, otherwise reset meta text.
                    if (!solver.isMoveLegal()) {
                        metaInformationLabel.setText("Invalid move, try again.");
                        metaInformationLabel.setFill(Color.RED);
                    } else {
                        metaInformationLabel.setText("");
                    }
                    
                    // If the problem is solved, indicate so.
                    if (solver.isProblemSolved()) {
                        metaInformationLabel.setText("Problem finished in " + solver.getMoveCount() + " moves! Reset to play again.");
                        metaInformationLabel.setFill(Color.GREEN);
                    }
                }
            });
        });
        
        if (moveButtons.size() > 4 && moveButtons.size() <= 8) {
            HBox buttonCols = new HBox();
            VBox col1 = new VBox();
            VBox col2 = new VBox();
            
            col1.setSpacing(10);
            col2.setSpacing(10);
            buttonCols.setSpacing(20);
            
            moveButtons.stream().forEach(b -> b.setPrefWidth(200));
            
            buttonCols.getChildren().addAll(col1, col2);
            for (int i = 0; i < 4; ++i) {
                col1.getChildren().add(moveButtons.get(i));
            }
            
            for (int i = 4; i < moveButtons.size(); ++i) {
                col2.getChildren().add(moveButtons.get(i));
            }
            
            stateMoves.getChildren().add(buttonCols);
        } else if (moveButtons.size() > 8) {
            
            HBox buttonCols = new HBox();
            VBox col1 = new VBox();
            VBox col2 = new VBox();
            VBox col3 = new VBox();
            
            col1.setSpacing(10);
            col2.setSpacing(10);
            col3.setSpacing(10);
            buttonCols.setSpacing(10);
            
            moveButtons.stream().forEach(b -> b.setPrefWidth(160));
            
            buttonCols.getChildren().addAll(col1, col2, col3);
            for (int i = 0; i < 5; ++i) {
                col1.getChildren().add(moveButtons.get(i));
            }
            
            for (int i = 5; i < 10; ++i) {
                col2.getChildren().add(moveButtons.get(i));
            }
            
            for (int i = 10; i < moveButtons.size(); ++i) {
                col3.getChildren().add(moveButtons.get(i));
            }
            
            stateMoves.getChildren().add(buttonCols);
        } else {
            moveButtons.stream().forEach(stateMoves.getChildren()::add);
        }
        
        stateMoves.setSpacing(10);
        
        // Add the current state section, state moves section, and goal state section to the state control container
        stateControl.getChildren().addAll(currStateSection, stateMoves, goalStateSection);
        stateControl.setAlignment(Pos.CENTER);
        
        // Make a container to organize the meta state stuff: reset button and information
        VBox metaStateContainer = new VBox();
        metaStateContainer.setAlignment(Pos.CENTER);
        
        // Style and position the meta information label
        metaInformationLabel.setFont(META_INFO_FONT);
        metaInformationLabel.setTextAlignment(TextAlignment.CENTER);
        
        // Make a container to position the meta information label
        HBox metaInformationLabelContainer = new HBox();
        metaInformationLabelContainer.setPadding(new Insets(0, 0, 10, 0));
        metaInformationLabelContainer.setAlignment(Pos.CENTER);
        metaInformationLabelContainer.getChildren().add(metaInformationLabel);
        
        // Create, style, and assign action to the reset state button
        Button resetState = new Button("Reset State");
        resetState.setPrefWidth(150);
        resetState.setFont(BUTTON_FONT);
        
        // Add the meta information and reset button to the meta state container
        metaStateContainer.getChildren().addAll(metaInformationLabelContainer, resetState);
        
        // Style and position the container for the automatic solver and statistics
        HBox solverContainer = new HBox();
        solverContainer.setAlignment(Pos.CENTER);
        
        Button next = new Button("Next");
        ChoiceBox searchType = new ChoiceBox();
        ChoiceBox benchmarkChoice = new ChoiceBox();
        Label statistics = new Label();
        
        // Search type options
        final String BFSSS = "BF State Space Search";
        final String DFSSS = "DF State Space Search";
        final String BESTFS = "Best-First Search";
        final String ASTARS = "A* Search";
        
        Button solve = new Button("Solve");
        solve.setPrefWidth(100);
        solve.setFont(BUTTON_FONT);
        
        solve.setOnAction(e -> {
            if (searchType.getValue() == null) return;
            final String search = searchType.getValue().toString();
            solve.setDisable(true);
            next.setDisable(false);
            moveButtons.forEach(b -> b.setDisable(true));
            benchmarkChoice.setDisable(true);
            
            switch (search) {
                case BFSSS:
                    bfsSolver = new BFSStateSpaceSolver(problem);
                    bfsSolver.solve();
                    setSolution(bfsSolver.getSolution());
                    statistics.setText(bfsSolver.getStatistics().toString());
                    break;
                case DFSSS:
                    dfsSolver = new DFSStateSpaceSolver(problem);
                    dfsSolver.solve();
                    setSolution(dfsSolver.getSolution());
                    statistics.setText(dfsSolver.getStatistics().toString());
                    break;
                case BESTFS:
                    bestSolver = new BestFirstSolver(problem);
                    bestSolver.solve();
                    setSolution(bestSolver.getSolution());
                    statistics.setText(bestSolver.getStatistics().toString());
                    break;
                case ASTARS:
                    aStarSolver = new AStarSolver(problem);
                    aStarSolver.solve();
                    setSolution(aStarSolver.getSolution());
                    statistics.setText(aStarSolver.getStatistics().toString());
                    break;
            }
            
            problem.setCurrentState((State) getSolution().next().getData());
            currState.setText(problem.getCurrentState().toString());
        });
        
        resetState.setOnAction(e -> {
            solver.reset();
            metaInformationLabel.setText("");
            currState.setText(problem.getCurrentState().toString());
            moveCount.setText("Moves (" + solver.getMoveCount() + " so far):");
            
            moveButtons.forEach(b -> b.setDisable(false));
            benchmarkChoice.setDisable(false);
            solve.setDisable(false);
            next.setDisable(true);
            
            moveButtons.stream().forEach(b -> {
                    b.setBackground(solve.getBackground());
            });
        });
        
        next.setPrefWidth(100);
        next.setFont(BUTTON_FONT);
        next.setOnAction(e -> {
            final String search = searchType.getValue().toString();
            
            if (getSolution().hasNext()) {
                State nextState = (State) getSolution().next().getData();
                solver.update(nextState);
                currState.setText(problem.getCurrentState().toString());
                moveCount.setText("Moves (" + solver.getMoveCount() + " so far):");
                moveButtons.stream().forEach(b -> {
                    b.setBackground(new Background(new BackgroundFill(Color.WHEAT, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
                });
                moveButtons.stream().filter(b -> b.getText().equals(nextState.getMove())).forEach(b -> {
                    b.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
                });
            }
            
            if (!getSolution().hasNext()) {
                next.setDisable(true);
                metaInformationLabel.setText("Problem solved with help");
                metaInformationLabel.setFill(Color.ORANGE);
            }
        });
        next.setDisable(true);
        
        VBox solveNextContainer = new VBox();
        solveNextContainer.getChildren().addAll(solve, next);
        solveNextContainer.setAlignment(Pos.CENTER);
        solveNextContainer.setSpacing(10);
        
        searchType.getItems().addAll(BFSSS, DFSSS, BESTFS, ASTARS);
        
        statistics.setFont(STATS_FONT);
        
        // Make a container to properly position and style the statistics
        HBox statisticsContainer = new HBox();
        statisticsContainer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderStroke.MEDIUM)));
        statisticsContainer.setPadding(new Insets(5, 5, 5, 5));
        statisticsContainer.setAlignment(Pos.CENTER);
        statisticsContainer.setPrefWidth(STATS_WIDTH);
        statisticsContainer.setPrefHeight(STATS_HEIGHT);
        statisticsContainer.getChildren().add(statistics);
        
        problem.getBenchmarks().stream().forEach(bnch -> benchmarkChoice.getItems().add(bnch.getName()));
        benchmarkChoice.setOnAction(e -> {
            final String selection = benchmarkChoice.getValue().toString();
            Benchmark b = problem.getBenchmarks().stream().filter(bnch -> bnch.getName().equals(selection)).findFirst().get();
            problem.setCurrentState(b.getStart());
            problem.setFinalState(b.getGoal()); 
            currState.setText(problem.getCurrentState().toString());
            goalState.setText(problem.getFinalState().toString());
        });
        
        solverContainer.setPrefWidth(1300);
        solverContainer.setSpacing(20);
        solverContainer.getChildren().addAll(solveNextContainer, searchType, statisticsContainer, benchmarkChoice);
        
        // Add all parts of the scene to the master HBox, center everything, and set the window size
        super.getChildren().addAll(welcomeContainer, introContainer, stateControl, metaStateContainer, solverContainer);
        super.setAlignment(Pos.CENTER);
        super.setPadding(new Insets(0, 0, 20, 0));
        super.setSpacing(20);
        super.setPrefSize(width, height);
    }
}
