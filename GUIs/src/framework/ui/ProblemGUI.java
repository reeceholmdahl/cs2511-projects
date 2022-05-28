/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import framework.problem.Problem;
import framework.solution.SolvingAssistant;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author rholm
 */
public class ProblemGUI extends VBox {
    
    // Constants related to the graphical representation of the problem
    private static final int STATE_SIZE = 400;
    private static final int BUTTON_WIDTH = 300;
    private static final Font WELCOME_FONT = Font.font("sans-serif", FontWeight.BOLD, 48);
    private static final Font INTRO_FONT = Font.font("serif", FontWeight.LIGHT, 24);
    private static final Font STATE_LABEL_FONT = Font.font("serif", FontWeight.BOLD, 36);
    private static final Font STATE_FONT = Font.font("monospace", 30);
    private static final Font BUTTON_FONT = Font.font("serif", 24);
    private static final Font META_INFO_FONT = Font.font("serif", FontWeight.MEDIUM, 24);
    
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
        welcomeContainer.setPadding(new Insets(20, 20, 20, 20));
        
        // Create and customize the intro label
        Label intro = new Label(problem.getIntroduction());
        intro.setFont(INTRO_FONT);
        intro.setWrapText(true);
        
        // Make a container to properly position the intro label
        HBox introContainer = new HBox();
        introContainer.getChildren().add(intro);
        introContainer.setAlignment(Pos.BASELINE_CENTER);
        introContainer.setPadding(new Insets(20, 20, 20, 20));
        
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
        problem.getMover().getMoveNames().forEach(move -> {
            
            // Create and style the button
            Button button = new Button(move);
            button.setFont(BUTTON_FONT);
            button.setPrefWidth(BUTTON_WIDTH);
            
            // Create two spacers to make a buffer around the button
            Region spacer1 = new Region();
            spacer1.setPrefSize(10, 15);
            Region spacer2 = new Region();
            spacer2.setPrefSize(10, 15);
            
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
            
            // Add the button and spacers to the stateMoves container
            stateMoves.getChildren().addAll(spacer1, button, spacer2);
        });
        
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
        metaInformationLabelContainer.setPadding(new Insets(20, 0, 20, 0));
        metaInformationLabelContainer.setAlignment(Pos.CENTER);
        metaInformationLabelContainer.getChildren().add(metaInformationLabel);
        
        // Create, style, and assign action to the reset state button
        Button resetState = new Button("Reset State");
        resetState.setPrefWidth(120);
        resetState.setFont(BUTTON_FONT);
        resetState.setOnAction(e -> {
            solver.reset();
            currState.setText(problem.getCurrentState().toString());
            moveCount.setText("Moves (" + solver.getMoveCount() + " so far):");
        });
        
        // Add the meta information and reset button to the meta state container
        metaStateContainer.getChildren().addAll(metaInformationLabelContainer, resetState);
        
        // Add all parts of the scene to the master HBox, center everything, and set the window size
        super.getChildren().addAll(welcomeContainer, introContainer, stateControl, metaStateContainer);
        super.setAlignment(Pos.CENTER);
        super.setPrefSize(width, height);
    }
}
