package calculator;

/**
 * appropriate comment here
 * @author your name here
 */
public class PrecedenceCalculator extends NoPrecedenceCalculator {
    
    public PrecedenceCalculator(String title) {
        super(title);
    }
    
    public PrecedenceCalculator() {
        this("Calculator With Operator Precedence");
    }
    
    @Override
    public void reduce() {
	if (numOpNumOnStack()) {
            if (getDispenser().tokenIsEOF()) {
                reduceNumOpNum();
                reduce();
            } else {
                char nextOp = (Character)getDispenser().getToken();
                char lastOp = (Character)getStack().get(getStack().size() - 2);

                if (!greaterOpPrecedence(nextOp, lastOp)) {
                    reduceNumOpNum();
                    reduce();
                }
            }
        }
    }
    
    protected boolean greaterOpPrecedence(char lhs, char rhs) {
        if (lhs == '*' || lhs == '/') {
            if (rhs == '*' || rhs == '/') return false;
            if (rhs == '+' || rhs == '-') return true;
        }
        return false;
    }
}