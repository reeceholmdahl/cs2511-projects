/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.myfirstapp.domains.puzzle15;

import com.example.myfirstapp.domains.puzzle.PuzzleState;
import com.example.myfirstapp.framework.problem.Problem;
import com.example.myfirstapp.framework.problem.Benchmark;

public class Puzzle15Problem extends Problem {
    
        public Puzzle15Problem() {
            super();
            super.setName("15-Puzzle");
            super.setIntroduction(INTRO);
            super.setMover(new Puzzle15Mover());
            super.setInitialState(new PuzzleState(new int[][]{{5, 1, 2, 3}, {6, 0, 7, 4}, {9, 10, 11, 8}, {13, 14, 15, 12}}));
            super.setCurrentState(super.getInitialState());
            super.setFinalState(new PuzzleState(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}}));
            super.addBenchmark(new Benchmark("15-Puz 1", 8, super.getInitialState(), super.getFinalState()));
            super.addBenchmark(new Benchmark("15-Puz 2", 16, new PuzzleState(new int[][]{{5, 1, 2, 4}, {6, 11, 12, 3}, {9, 7, 0, 8}, {13, 10, 14, 15}}), super.getFinalState())); 
            super.addBenchmark(new Benchmark("15-Puz 3", 33, new PuzzleState(new int[][]{{1, 4, 10, 8}, {2, 6, 0, 12}, {3, 14, 15, 11}, {5, 9, 13, 7}}), super.getFinalState())); 
            super.addBenchmark(new Benchmark("15-Puz 4", 38, new PuzzleState(new int[][]{{7, 13, 15, 2}, {10, 1, 11, 4}, {3, 9, 6, 8}, {5, 0, 14, 12}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("15-Puz 5", 42, new PuzzleState(new int[][]{{10, 15, 5, 8}, {2, 0, 6, 3}, {1, 12, 14, 4}, {9, 7, 13, 11}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("15-Puz 6", 44, new PuzzleState(new int[][]{{14, 6, 0, 8}, {2, 1, 4, 9}, {7, 13, 10, 11}, {15, 5, 12, 3}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("15-Puz 7", 50, new PuzzleState(new int[][]{{7, 8, 4, 11}, {12, 14, 10, 15}, {0, 5, 3, 13}, {2, 1, 9, 6}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("15-Puz 8", 54, new PuzzleState(new int[][]{{7, 14, 0, 9}, {10, 2, 11, 13}, {6, 15, 4, 12}, {5, 1, 8, 3}}), super.getFinalState()));
        }

        private static final String INTRO = 
                "There is a 4x4 tile board filled with tiles numbered 1 through 15. There is one empty space. You can "
              + "only slide a tile if it is touching the empty space, switching its location with the empty space. "
              + "To complete the puzzle, the first row must be '1 2 3, 4', the second row must be '5, 6, 7, 8', the "
              + "third row must be '9, 10, 11, 12', and the fourth row must be '13, 14, 15, EMPTY. You must devise a "
              + "sequence of actions so that you reach this desired final state.";

}