/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.myfirstapp.domains.farmer;

import com.example.myfirstapp.framework.problem.Mover;
import com.example.myfirstapp.framework.problem.State;

public class FarmerMover extends Mover {

        private static final String GOES_ALONE = "Go Alone";
        private static final String TAKES_WOLF = "Take Wolf";
        private static final String TAKES_GOAT = "Take Goat";
        private static final String TAKES_CABBAGE = "Take Cabbage";
    
        public FarmerMover() {
            super.addMove(GOES_ALONE, s -> goesAlone(s));
            super.addMove(TAKES_WOLF, s -> takesWolf(s));
            super.addMove(TAKES_GOAT, s -> takesGoat(s));
            super.addMove(TAKES_CABBAGE, s -> takesCabbage(s));
        }

        private State goesAlone(State state) {
            FarmerState prev = (FarmerState) state;

            if (prev.equals(new FarmerState("West", "West", "West", "West"))
             || prev.equals(new FarmerState("West", "West", "West", "East"))
             || prev.equals(new FarmerState("West", "East", "West", "West"))) {
                return illegalMove(state);
            }
            
            return new FarmerState((prev.getFarmer().equals("West") ? "East" : "West"), prev.getWolf(), prev.getGoat(), prev.getCabbage());
        }

        private State takesWolf(State state) {
            FarmerState prev = (FarmerState) state;

            if (!prev.getFarmer().equals(prev.getWolf()) || prev.getGoat().equals(prev.getCabbage())) {
                return illegalMove(state);
            }
            
            return new FarmerState((prev.getFarmer().equals("West") ? "East" : "West"), (prev.getWolf().equals("West") ? "East" : "West"), prev.getGoat(), prev.getCabbage());
        }

        private State takesGoat(State state) {
            FarmerState prev = (FarmerState) state;

            if (!prev.getFarmer().equals(prev.getGoat())) {
                return illegalMove(state);
            }
            
            return new FarmerState((prev.getFarmer().equals("West") ? "East" : "West"), prev.getWolf(), (prev.getGoat().equals("West") ? "East" : "West"), prev.getCabbage());
        }
        
        private State takesCabbage(State state) {
            FarmerState prev = (FarmerState) state;
            if (prev.equals(new FarmerState("West", "East", "West", "West"))) {
                return new FarmerState("East", "East", "West", "East");
            } else if (prev.equals(new FarmerState("West", "West", "East", "West"))) {
                return new FarmerState("East", "West", "East", "East");
            }
            
            return illegalMove(state);
        }

        private State illegalMove(State state) {
            return null;
        }
    }
