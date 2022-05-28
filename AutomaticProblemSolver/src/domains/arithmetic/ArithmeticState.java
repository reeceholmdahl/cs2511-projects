/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.arithmetic;

import framework.problem.State;

/**
 *
 * @author rholm
 */
public class ArithmeticState extends State {
    
    /**
     * Creates a new arithmetic state object.
     * @param value The value of this arithmetic state.
     */
    public ArithmeticState(int value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
        ArithmeticState otherState = (ArithmeticState) other;
        return this.value == otherState.value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.value;
        return hash;
    }
    
    @Override
    public String toString() {
        return "The value is: " + value;
    }
    
    /**
     * Retrieves the value of the current arithmetic state.
     * @return The value of the current arithmetic state.
     */
    public int getValue() {
        return value;
    }
    
    // Value of the arithmetic state.
    private final int value;
}
