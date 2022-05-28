/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domains.arithmetic;

import framework.problem.Mover;
import framework.problem.State;

/**
 *
 * @author rholm
 */
public class ArithmeticMover extends Mover {
    
    // Moves for the arithmetic problem
    public static final String ADD3 = "Add 3";
    public static final String SUB5 = "Subtract 5";
    public static final String DIV2 = "Divide by 2";
    public static final String MUL2 = "Multiply by 2";
    
    /**
     * Creates a new arithmetic mover object.
     */
    public ArithmeticMover() {
        super.addMove(ADD3, s -> add3(s));
        super.addMove(SUB5, s -> sub5(s));
        super.addMove(DIV2, s -> div2(s));
        super.addMove(MUL2, s -> mul2(s));
    }
    
    /**
     * Adds 3 to the value and returns a new state.
     * @param state The state of reference.
     * @return A new state with the value of the passed state plus 3.
     */
    private State add3(State state) {
        ArithmeticState aState = (ArithmeticState)state;
        return new ArithmeticState(aState.getValue() + 3);
    }
    
    /**
     * Subtracts 5 from the value and returns a new state.
     * @param state The state of reference.
     * @return A new state with the value of the passed state minus 5.
     */
    private State sub5(State state) {
        ArithmeticState aState = (ArithmeticState)state;
        return new ArithmeticState(aState.getValue() - 5);
    }
    
    /**
     * Divides the value by 2 and returns a new state.
     * @param state The state of reference.
     * @return A new state with the value of the passed state divided by 2.
     */
    private State div2(State state) {
        ArithmeticState aState = (ArithmeticState)state;
        return new ArithmeticState(aState.getValue() / 2);
    }
    
    /**
     * Multiplies the value by 2 and returns a new state.
     * @param state The state of reference.
     * @return A new state with the value of the passed state multiplied by 2.
     */
    private State mul2(State state) {
        ArithmeticState aState = (ArithmeticState)state;
        return new ArithmeticState(aState.getValue() * 2);
    }
}
