/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.myfirstapp.domains.puzzle;

import com.example.myfirstapp.framework.problem.Mover;
import com.example.myfirstapp.framework.problem.State;

public class PuzzleMover extends Mover {

        private static final String SLIDE_TILE_1 = "Slide Tile 1";
        private static final String SLIDE_TILE_2 = "Slide Tile 2";
        private static final String SLIDE_TILE_3 = "Slide Tile 3";
        private static final String SLIDE_TILE_4 = "Slide Tile 4";
        private static final String SLIDE_TILE_5 = "Slide Tile 5";
        private static final String SLIDE_TILE_6 = "Slide Tile 6";
        private static final String SLIDE_TILE_7 = "Slide Tile 7";
        private static final String SLIDE_TILE_8 = "Slide Tile 8";  
    
        public PuzzleMover() {
            super.addMove(SLIDE_TILE_1, s -> slideTile(s, 1));
            super.addMove(SLIDE_TILE_2, s -> slideTile(s, 2));
            super.addMove(SLIDE_TILE_3, s -> slideTile(s, 3));
            super.addMove(SLIDE_TILE_4, s -> slideTile(s, 4));
            super.addMove(SLIDE_TILE_5, s -> slideTile(s, 5));
            super.addMove(SLIDE_TILE_6, s -> slideTile(s, 6));
            super.addMove(SLIDE_TILE_7, s -> slideTile(s, 7));
            super.addMove(SLIDE_TILE_8, s -> slideTile(s, 8));
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
