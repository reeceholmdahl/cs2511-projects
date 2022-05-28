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
import domains.puzzle15.Puzzle15Problem;
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
        
        final int WIDTH = 1500;
        final int HEIGHT = 900;
        
        Tab dummy = new Tab();
        dummy.setText("Dummy Problem");
        dummy.setContent(new ProblemGUI(new DummyProblem(), WIDTH, HEIGHT));
        
        Tab arith = new Tab();
        arith.setText("Arithmetic Problem");
        arith.setContent(new ProblemGUI(new ArithmeticProblem(), WIDTH, HEIGHT));
        
        Tab farmer = new Tab();
        farmer.setText("Farmer Problem");
        farmer.setContent(new ProblemGUI(new FarmerProblem(), WIDTH, HEIGHT));
        
        Tab puzzle8 = new Tab();
        puzzle8.setText("8-Puzzle Problem");
        puzzle8.setContent(new ProblemGUI(new PuzzleProblem(), WIDTH, HEIGHT));
        
        Tab puzzle15 = new Tab();
        puzzle15.setText("15-Puzzle Problem");
        puzzle15.setContent(new ProblemGUI(new Puzzle15Problem(), WIDTH, HEIGHT));
        
        tabPane.getTabs().addAll(farmer, arith, puzzle8, puzzle15, dummy);
        
        
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
