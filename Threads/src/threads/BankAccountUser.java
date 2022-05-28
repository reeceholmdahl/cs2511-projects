package threads;

import java.lang.Thread;

/**
 * A class representing a bank account user.
 * @author tcolburn
 */
public class BankAccountUser extends Thread {

    /**
     * Creates a new bank account user.
     * @param name the user's name
     * @param account the account used
     * @param transactions an array of integers representing deposits (positive)
     * and withdrawals (negative)
     */
    public BankAccountUser(String name, BankAccount account, int[] transactions) {
        super(name);
        this.account = account;
        this.transactions = transactions;
        transactionsRemaining = transactions == null ? 0 : transactions.length;
    }

    /**
     * Getter for the user's name
     * @return the user's name
     */
//    public String getName() {
//        return name;
//    }

    /**
     * Getter for the number of transactions remaining for this user.
     * @return the number of transactions remaining
     */
    public int getTransactionsRemaining() {
        return transactionsRemaining;
    }
    
    public boolean isWaiting() {
        return waiting;
    }
    
    public void setWaiting(boolean state) {
        waiting = state;
    }
    
    public boolean isFinished() {
        return getTransactionsRemaining() <= 1;
    }
    
    /**
     * Runs the transactions in a loop.
     * When finished, a message is logged.
     */
    public void run() {
        if (transactions == null) return;
        for (int amount : transactions) {
            if (amount > 0) {
                account.deposit(amount, this);
            }
            else if (amount < 0) {
                try {
                    account.withdraw(Math.abs(amount), this);
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
            else {
                // amount will not be zero
            }
            transactionsRemaining--;
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
    
//    private final String name;
    private boolean waiting = false;
    private final BankAccount account;
    private final int[] transactions;
    private int transactionsRemaining;
}
