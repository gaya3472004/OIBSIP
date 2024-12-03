import java.util.ArrayList;
import java.util.List;

public class Account {
    private String userID;
    private String userPIN;
    private double balance;
    private List<String> transactionHistory;

    public Account(String userID, String userPIN, double initialBalance) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserID() {
        return userID;
    }

    public boolean verifyPIN(String pin) {
        return this.userPIN.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add("Deposited: $" + amount);
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
            return true;
        }
        return false;
    }

    public boolean transfer(Account toAccount, double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            toAccount.deposit(amount);
            transactionHistory.add("Transferred: $" + amount + " to " + toAccount.getUserID());
            return true;
        }
        return false;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}