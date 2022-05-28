/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.puzzle;

import framework.problem.Problem;

public class PuzzleProblem extends Problem {
    
        public PuzzleProblem() {
            super();
            super.setName("Puzzle");
            super.setIntroduction(INTRO);
            super.setMover(new PuzzleMover());
            super.setInitialState(new PuzzleState(new int[][]{{2, 8, 3}, {1, 6, 4}, {7, 0, 5}}));
            super.setCurrentState(super.getInitialState());
            super.setFinalState(new PuzzleState(new int[][]{{1, 2, 3}, {8, 0, 4}, {7, 6, 5}}));
        }

        private static final String INTRO = 
                "There is a 3x3 tile board filled with tiles numbered 1 throug 8. There is one empty space. You can "
              + "only slide a tile if it is touching the empty space, switching its location with the empty space. "
              + "To complete the puzzle, the top row must be '1 2 3', the middle row must be '8 EMPTY 4' and the "
              + "bottom row must be '7 6 5'. You must devise a sequence of actions so that you reach this desired "
              + "final state.";

}