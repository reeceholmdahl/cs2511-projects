package calculator;

/**
 * appropriate comment here
 * @author your name here
 */
public class NoPrecedenceCalculator extends SimpleCalculator {
    
    public NoPrecedenceCalculator(String title) {
        super(title);
    }
    
    public NoPrecedenceCalculator() {
        this("Calculator Without Operator Precedence");
    }
    
    @Override
    public double evaluate() {
	setState(State.START);
        while (true) {
            switch (getState()) {
                case START: 
                    start(); 
                    break;
                case NUMBER:
                    number(); 
                    break;
                case OPERATOR:
                    operator(); 
                    break;
                case END:
                    end();
                    return (Double)getStack().pop();
                default: 
                    throw new Error("Something is wrong in SimpleCalculator.evaluate"); // shouldn't happen
            }
        }
    }

    private void start() {
        getDispenser().advance();
        if (!getDispenser().tokenIsNumber()) {
            syntaxError(NUM);
        }
        setState(State.NUMBER);
    }
    
    /**
     * Performs the processing in the state named NUMBER1.
     * The number is shifted onto the stack and the next token is parsed.
     * If the token is EOF the state is changed to END.
     * If the token is an operator the state is changed to OPERATOR.
     * Otherwise an error is signaled.
     */
    private void number() {
        shift();
        getDispenser().advance();
        if (getDispenser().tokenIsEOF()) {
            setState(State.END);
        } else if (getDispenser().tokenIsOperator()) {
            setState(State.OPERATOR);
        } else {
            syntaxError(OP_OR_END);
        }
    }
    
    /**
     * Performs the processing in the state named OPERATOR.
     * The operator is shifted onto the stack and the next token is parsed.
     * If the next token is not a number an error is signaled.
     * Otherwise the state is changed to NUMBER2.
     */
    private void operator() {
        reduce();
        shift();
        getDispenser().advance();
        if (!getDispenser().tokenIsNumber()) {
            syntaxError(NUM);
        }
        setState(State.NUMBER);
    }
}