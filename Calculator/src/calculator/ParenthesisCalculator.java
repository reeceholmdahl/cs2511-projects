package calculator;

/**
 * appropriate comment here
 * @author your name here
 */
public class ParenthesisCalculator extends PrecedenceCalculator {
    
    public ParenthesisCalculator(String title) {
        super(title);
    }
    
    public ParenthesisCalculator() {
        this("Calculator With Operator Precedence and Parentheses");
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
                case LEFT_PAREN:
                    leftParen();
                    break;
                case RIGHT_PAREN:
                    rightParen();
                    break;
                case END:
                    end();
                    return (Double)getStack().pop();
                default: 
                    throw new Error("Something is wrong in SimpleCalculator.evaluate"); // shouldn't happen
            }
        }
    }

    @Override
    public void reduce() {
	if (numOpNumOnStack()) {
            if (getDispenser().tokenIsEOF()) {
                reduceNumOpNum();
                reduce();
            } else { //else if (!getDispenser().tokenIsLeftParen()) {
                char nextOp = (Character)getDispenser().getToken();
                char lastOp = (Character)getStack().get(getStack().size() - 2);

                if (!greaterOpPrecedence(nextOp, lastOp)) {
                    reduceNumOpNum();
                    reduce();
                }
            }
        }
    }
    
    private void reduceUntilOpenParen() {
        if (numOpNumOnStack()) {
            char top = 'a';
            try {
                top = (Character)getStack().get(getStack().size() - 1);
            } catch (Exception e) {
                System.err.println(e);
            }
            
            if (getStack().size() >= 1 && top != '(') {
                reduceNumOpNum();
                reduceUntilOpenParen();
            }
        }
    }
    
    private void start() {
        getDispenser().advance();
        if (getDispenser().tokenIsNumber()) {
            setState(State.NUMBER);
        } else if (getDispenser().tokenIsLeftParen()) {
            setState(State.LEFT_PAREN);
        } else {
            syntaxError(NUM_OR_LEFT_PAREN);
        }
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
        } else if (getDispenser().tokenIsRightParen()) {
            setState(State.RIGHT_PAREN);
        } else {
            syntaxError(OP_OR_END_OR_RIGHT_PAREN);
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
        if (getDispenser().tokenIsNumber()) {
            setState(State.NUMBER);
        } else if (getDispenser().tokenIsLeftParen()) {
            setState(State.LEFT_PAREN);
        } else {
            syntaxError(NUM_OR_LEFT_PAREN);
        }
    }
    
    private void leftParen() {
        shift();
        getDispenser().advance();
        if (!getDispenser().tokenIsNumber()) {
            syntaxError(NUM);
        }
        setState(State.NUMBER);
    }
    
    private void rightParen() {
        shift();
        getDispenser().advance();
        getStack().pop();
        reduceUntilOpenParen();
        double reduction = (Double)getStack().pop();
        getStack().pop();
        getStack().push(reduction);
        if (getDispenser().tokenIsOperator()) {
            setState(State.OPERATOR);
        } else if (getDispenser().tokenIsEOF()) {
            setState(State.END);
        } else if (getDispenser().tokenIsRightParen()) {
            setState(State.RIGHT_PAREN);
        } else {
            syntaxError(OP_OR_END);
        }
    }
    
    public static final String NUM_OR_LEFT_PAREN = "number or left paren";
    public static final String OP_OR_END_OR_RIGHT_PAREN = "operator, end of input, or right paren";
}