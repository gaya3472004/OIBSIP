import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;

    public Bank() {
        accounts = new HashMap<>();
        // Pre-creating some accounts
        accounts.put("user1", new Account("user1", "1234", 500.00));
        accounts.put("user2", new Account("user2", "5678", 300.00));
    }

    public Account authenticate(String userID, String pin) {
        Account account = accounts.get(userID);
        if (account != null && account.verifyPIN(pin)) {
            return account;
        }
        return null;
    }

    public Account getAccount(String userID) {
        return accounts.get(userID);
    }
}