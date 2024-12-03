import java.util.Scanner;

public class ATM {
    private Bank bank;
    private Scanner scanner;

    public ATM() {
        bank = new Bank();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        Account userAccount = bank.authenticate(userID, pin);

        if (userAccount != null) {
            System.out.println("Login successful.");
            showMenu(userAccount);
        } else {
            System.out.println("Invalid User ID or PIN.");
        }
    }

    private void showMenu(Account account) {
        boolean quit = false;
        while (!quit) {
            System.out.println("\nATM Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Transaction History");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposited: $" + depositAmount);
                    break;
                case 3:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawalAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawalAmount)) {
                        System.out.println("Withdrew: $" + withdrawalAmount);
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                    break;
                case 4:
                    System.out.print("Enter recipient User ID: ");
                    String recipientID = scanner.next();
                    Account recipient = bank.getAccount(recipientID);
                    if (recipient != null) {
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        if (account.transfer(recipient, transferAmount)) {
                            System.out.println("Transferred: $" + transferAmount + " to " + recipientID);
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                    } else {
                        System.out.println("Recipient account not found.");
                    }
                    break;
                case 5:
                    System.out.println("Transaction History:");
                    for (String transaction : account.getTransactionHistory()) {
                        System.out.println(transaction);
                    }
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM.");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }
}
