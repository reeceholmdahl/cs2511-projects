/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.myfirstapp.domains.puzzle15;

import com.example.myfirstapp.domains.puzzle.PuzzleState;
import com.example.myfirstapp.framework.problem.Mover;
import com.example.myfirstapp.framework.problem.State;

public class Puzzle15Mover extends Mover {

        private static final String SLIDE_TILE_1 = "Slide Tile 1";
        private static final String SLIDE_TILE_2 = "Slide Tile 2";
        private static final String SLIDE_TILE_3 = "Slide Tile 3";
        private static final String SLIDE_TILE_4 = "Slide Tile 4";
        private static final String SLIDE_TILE_5 = "Slide Tile 5";
        private static final String SLIDE_TILE_6 = "Slide Tile 6";
        private static final String SLIDE_TILE_7 = "Slide Tile 7";
        private static final String SLIDE_TILE_8 = "Slide Tile 8";
        private static final String SLIDE_TILE_9 = "Slide Tile 9";
        private static final String SLIDE_TILE_10 = "Slide Tile 10";
        private static final String SLIDE_TILE_11 = "Slide Tile 11";
        private static final String SLIDE_TILE_12 = "Slide Tile 12";
        private static final String SLIDE_TILE_13 = "Slide Tile 13";
        private static final String SLIDE_TILE_14 = "Slide Tile 14";
        private static final String SLIDE_TILE_15 = "Slide Tile 15";
    
        public Puzzle15Mover() {
            super.addMove(SLIDE_TILE_1, s -> slideTile(s, 1));
            super.addMove(SLIDE_TILE_2, s -> slideTile(s, 2));
            super.addMove(SLIDE_TILE_3, s -> slideTile(s, 3));
            super.addMove(SLIDE_TILE_4, s -> slideTile(s, 4));
            super.addMove(SLIDE_TILE_5, s -> slideTile(s, 5));
            super.addMove(SLIDE_TILE_6, s -> slideTile(s, 6));
            super.addMove(SLIDE_TILE_7, s -> slideTile(s, 7));
            super.addMove(SLIDE_TILE_8, s -> slideTile(s, 8));
            super.addMove(SLIDE_TILE_9, s -> slideTile(s, 9));
            super.addMove(SLIDE_TILE_10, s -> slideTile(s, 10));
            super.addMove(SLIDE_TILE_11, s -> slideTile(s, 11));
            super.addMove(SLIDE_TILE_12, s -> slideTile(s, 12));
            super.addMove(SLIDE_TILE_13, s -> slideTile(s, 13));
            super.addMove(SLIDE_TILE_14, s -> slideTile(s, 14));
            super.addMove(SLIDE_TILE_15, s -> slideTile(s, 15));
        }

        private State slideTile(State state, int tile) {
            PuzzleState pState = (PuzzleState) state;
            
            PuzzleState.Location tileLoc = pState.getLocation(tile);
            PuzzleState.Location emptyLoc = pState.getLocation(0);
          
            if (Math.abs(tileLoc.getRow() - emptyLoc.getRow()) + Math.abs(tileLoc.getColumn() - emptyLoc.getColumn()) > 1) {
                return illegalMove(state);
            }
            
            return new PuzzleState(pState, tileLoc, emptyLoc);
        }

        private State illegalMove(State state) {
            return null;
        }
    }
