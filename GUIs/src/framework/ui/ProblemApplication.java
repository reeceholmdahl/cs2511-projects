/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework.ui;

import domains.dummy.DummyProblem;
import domains.arithmetic.ArithmeticProblem;
import domains.farmer.FarmerProblem;
import domains.puzzle.PuzzleProblem;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author rholm
 */
public class ProblemApplication extends Application {
    
    private TabPane tabPane;
    
    @Override
    public void start(Stage primaryStage) {
        tabPane = new TabPane();
        
        Tab dummy = new Tab();
        dummy.setText("Dummy Problem");
        dummy.setContent(new ProblemGUI(new DummyProblem(), 1300, 750));
        
        Tab arith = new Tab();
        arith.setText("Arithmetic Problem");
        arith.setContent(new ProblemGUI(new ArithmeticProblem(), 1300, 750));
        
        Tab farmer = new Tab();
        farmer.setText("Farmer Problem");
        farmer.setContent(new ProblemGUI(new FarmerProblem(), 1300, 750));
        
        Tab puzzle = new Tab();
        puzzle.setText("Puzzle Problem");
        puzzle.setContent(new ProblemGUI(new PuzzleProblem(), 1300, 750));
        
        tabPane.getTabs().addAll(dummy, arith, farmer, puzzle);
        
        
        primaryStage.setTitle("Problem Solving Application");
        primaryStage.setScene(new Scene(tabPane));
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
