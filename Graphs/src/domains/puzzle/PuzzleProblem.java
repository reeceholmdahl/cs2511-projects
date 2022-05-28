/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.puzzle;

import framework.problem.Problem;
import framework.problem.Benchmark;

public class PuzzleProblem extends Problem {
    
        public PuzzleProblem() {
            super();
            super.setName("Puzzle");
            super.setIntroduction(INTRO);
            super.setMover(new PuzzleMover());
            super.setInitialState(new PuzzleState(new int[][]{{2, 8, 3}, {1, 6, 4}, {7, 0, 5}}));
            super.setCurrentState(super.getInitialState());
            super.setFinalState(new PuzzleState(new int[][]{{1, 2, 3}, {8, 0, 4}, {7, 6, 5}}));
            super.addBenchmark(new Benchmark("8-Puz 1", 5, super.getInitialState(), super.getFinalState()));
            super.addBenchmark(new Benchmark("8-Puz 2", 10, new PuzzleState(new int[][]{{3, 6, 4}, {1, 0, 2}, {8, 7, 5}}), super.getFinalState())); 
            super.addBenchmark(new Benchmark("8-Puz 3", 13, new PuzzleState(new int[][]{{3, 0, 4}, {1, 6, 5}, {8, 2, 7}}), super.getFinalState())); 
            super.addBenchmark(new Benchmark("8-Puz 4", 18, new PuzzleState(new int[][]{{2, 1, 3}, {8, 0, 4}, {6, 7, 5}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("8-Puz 5", 20, new PuzzleState(new int[][]{{4, 2, 0}, {8, 3, 6}, {7, 5, 1}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("8-Puz 6", 24, new PuzzleState(new int[][]{{1, 6, 3}, {4, 0, 8}, {7, 2, 5}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("8-Puz 7", 30, new PuzzleState(new int[][]{{5, 6, 7}, {4, 0, 8}, {3, 2, 1}}), super.getFinalState()));
            super.addBenchmark(new Benchmark("8-Puz 8", 30, new PuzzleState(new int[][]{{5, 2, 7}, {8, 0, 4}, {3, 6, 1}}), super.getFinalState()));
        }

        private static final String INTRO = 
                "There is a 3x3 tile board filled with tiles numbered 1 throug 8. There is one empty space. You can "
              + "only slide a tile if it is touching the empty space, switching its location with the empty space. "
              + "To complete the puzzle, the top row must be '1 2 3', the middle row must be '8 EMPTY 4' and the "
              + "bottom row must be '7 6 5'. You must devise a sequence of actions so that you reach this desired "
              + "final state.";

}