import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a bank account
class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit of $" + amount + " successful. New balance: $" + balance);
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawal of $" + amount + " successful. New balance: $" + balance);
        } else {
            System.out.println("Insufficient funds. Current balance: $" + balance);
        }
    }
}

// Represents a bank branch
class BankBranch {
    private String branchName;
    private List<BankAccount> accounts;

    public BankBranch(String branchName) {
        this.branchName = branchName;
        this.accounts = new ArrayList<>();
    }

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public void displayAccounts() {
        System.out.println("Accounts in " + branchName + ":");
        for (BankAccount account : accounts) {
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Account Holder: " + account.getAccountHolderName());
            System.out.println("Balance: $" + account.getBalance());
            System.out.println("---------------------------");
        }
    }
}

// Represents the bank application
class BankApp {
    private List<BankBranch> branches;

    public BankApp() {
        this.branches = new ArrayList<>();
    }

    public void addBranch(BankBranch branch) {
        branches.add(branch);
    }

    public void displayBranches() {
        System.out.println("Available Branches:");
        for (BankBranch branch : branches) {
            System.out.println(branch.getBranchName());
        }
    }
}

// Main class
public class BankApplication {
    public static void main(String[] args) {
        // Create an instance of the bank app
        BankApp bankApp = new BankApp();

        // Create some bank branches
        BankBranch branch1 = new BankBranch("Branch 1");
        BankBranch branch2 = new BankBranch("Branch 2");

        // Create bank accounts
        BankAccount account1 = new BankAccount("001", "John Doe", 1000);
        BankAccount account2 = new BankAccount("002", "Jane Smith", 500);

        // Add bank accounts to the branches
        branch1.addAccount(account1);
        branch2.addAccount(account2);

        // Add branches to the bank app
        bankApp.addBranch(branch1);
        bankApp.addBranch(branch2);

        // Display available branches
        bankApp.displayBranches();

        // Display accounts in a specific branch
        branch1.displayAccounts();

        // Perform banking operations
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();

        for (BankBranch branch : bankApp.getBranches()) {
            for (BankAccount account : branch.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    account.deposit(depositAmount);
                    break;
                }
            }
        }

        scanner.close();
    }
}
