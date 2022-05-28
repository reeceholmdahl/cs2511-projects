/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

/**
 *
 * @author rholm
 */
public class BankAccountRescuer extends BankAccountUser {
    
    private final BankAccountUser[] children;
    private final BankAccount account;
    
    public BankAccountRescuer(String name, BankAccount account, BankAccountUser[] children) {
        super(name, account, null);
        this.children = children;
        this.account = account;
    }
    
    private boolean allFinished() {
        boolean finished = true;
        for (BankAccountUser child : children) {
            if (!child.isFinished()) {
                finished = false;
                break;
            }
        }
        return finished;
    }   
    
    private boolean allWaiting() {
        boolean waiting = true;
        for (BankAccountUser child : children) {
            if (child.isFinished()) continue;
            if (!child.isWaiting()) {
                waiting = false;
            }
        }
        return waiting;
    }
    
    @Override
    public void run() {
        while (true) {
            if (allFinished()) {
                return;
            }
            if (!allFinished() && allWaiting()) {
                account.deposit(100, this);
            }
            try {
                Thread.sleep((int)(Math.random() * 100));
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
    }
}
