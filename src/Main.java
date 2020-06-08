import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean quit = false;
    private static ArrayList<Bank> banks = new ArrayList<Bank>();
    private static ArrayList<String> majorBanks = new ArrayList<>(Arrays.asList("CIBC", "TD", "ScotiaBank", "BMO", "RBC"));


    public static void main(String[] args) {
        String customerName;
        String senderName;
        String recipientName;
        String branchName;
        String userName;
        double initTransaction;
        double transaction;
        spawnBanks();
        System.out.println("Welcome to the ATM. Select you bank");
        printBanks();
        Bank bank = selectBank();
        System.out.println("Welcome to "+bank.getName());
        System.out.println("Are you a Bank root user[0] or Customer at an ATM[1]?");
        int mode = scanner.nextInt();scanner.nextLine();
        switch (mode){
            case 0:
                printRootOptions();
                while (!quit){
                    System.out.println("\n1 - Print Available Options");
                    int opt = scanner.nextInt();scanner.nextLine();
                    switch (opt){
                        case 0:
                            System.out.println("\nExiting [Root Mode] Bank...");
                            quit=true;
                            break;
                        case 1:
                            printRootOptions();
                            break;
                        case 2:
                            System.out.println("Enter name of customer");
                            customerName = scanner.nextLine();

                            System.out.println("Enter deposit amount");
                            initTransaction = scanner.nextDouble();scanner.nextLine();

                            System.out.println("Enter name of branch");
                            branchName = scanner.nextLine();

                            if (bank.addCustomer(branchName, customerName, initTransaction)){
                                System.out.println(customerName+" added to branch "+branchName);
                            }
                            else{
                                System.out.println("Error: Something went wrong. Please check inputs...");
                            }
                            break;
                        case 3:
                            System.out.println("Enter name of branch");
                            branchName = scanner.nextLine();
                            bank.showCustomers(branchName, true);
                            break;
                        case 4:
                            System.out.println("Enter name of branch");
                            branchName = scanner.nextLine();
                            bank.addBranch(branchName);
                            break;
                        case 5:
                            bank.listBranches();
                            break;
                    }
                }
                break;
            case 1:
                System.out.println("Entering ATM associated with "+bank.getName());
                System.out.println("What is your name?"); //Add security token
                userName = scanner.nextLine();
                Bank userBankAccount = queryBank(userName);
                if (userBankAccount != null) {
                    while (!quit) {
                        System.out.println("\n1 - Print Available Options");
                        int opt = scanner.nextInt();
                        scanner.nextLine();
                        switch (opt) {
                            case 0:
                                System.out.println("\nExiting [User Mode] Bank...");
                                quit = true;
                                break;
                            case 1:
                                printUserOptions();
                                break;
                            case 2:
                                System.out.println("Enter name of recipient");
                                recipientName = scanner.nextLine();

                                System.out.println("Enter sending amount");
                                transaction = scanner.nextDouble();
                                scanner.nextLine();

                                if (bank.addTransaction(userName, recipientName, transaction)) {
                                    System.out.println("Success: Transaction complete");
                                } else {
                                    System.out.println("Error: Something went wrong. Please check inputs.");
                                }
                                break;


                            case 3:
                                System.out.println("Enter withdraw amount");
                                transaction = scanner.nextDouble();
                                scanner.nextLine();
                                if (bank.customerWithdraw(bank.getName(), userName, transaction)) {
                                    System.out.println("Withdrew" + transaction);
                                } else {
                                    System.out.println("Error: Insufficient balance to withdraw");
                                }
                                break;
                            case 4:
                                System.out.println("Enter deposit amount");
                                transaction = scanner.nextDouble();
                                scanner.nextLine();
                                if (bank.customerDeposit(bank.getName(), userName, transaction)){
                                    System.out.println("Deposited " + transaction +" Successfully");
                                }
                                else{
                                    System.out.println("Error: Something went wrong. Did not deposit");
                                }

                                break;
                            case 5:
                                System.out.println("Your associated branch for ");
                        }
                    }
                    break;
                } else {
                    System.out.println("User does not have a bank account. Please register for an account at a bank. ");
                    break;
                }
            default:
                System.out.println("Not valid mode");
        }
    }

    private static Bank createBank(String name){
        return new Bank(name);
    }

    private static void spawnBanks(){
        for (int i = 0; i<majorBanks.size(); i++){
            banks.add(createBank(majorBanks.get(i)));
        }
    }
    private static Bank queryBank(String accountName){
        for (int i = 0; i<banks.size(); i++){
            if (banks.get(i).hasCustomer(accountName)){
                return banks.get(i);
            }
        }
        return null;
    }
    private static boolean hasAccount(String accountName){
        for (int i = 0; i<banks.size(); i++){
            if (banks.get(i).hasCustomer(accountName)){
                return true;
            }
        }
        return false;
    }

    private static void printBanks(){
        for (int i = 0; i<banks.size(); i++){
            System.out.println((i+1)+" - "+majorBanks.get(i));
        }
    }

    private static Bank selectBank() {
        while (true) {
            int opt = scanner.nextInt();scanner.nextLine();
            if (0<opt && opt<=banks.size()) {
                return banks.get(opt-1);
            }
            else if (opt!=0){
                System.out.println("Invalid Bank");
            }
            printBanks();
        }
    }
    private static void printRootOptions() {
        System.out.println("0 - Quit");
        System.out.println("1 - Print Options");
        System.out.println("2 - Add customer");
        System.out.println("3 - Show Customers");
        System.out.println("4 - Add branch");
        System.out.println("5 - list branches");
    }
    private static void printUserOptions() {
        System.out.println("0 - Quit");
        System.out.println("1 - Print Options");
        System.out.println("2 - Add transaction (E-transfer) to...)");
        System.out.println("3 - Customer Withdraw");
        System.out.println("4 - Customer Deposit");
        System.out.println("5 - Find my branch");
    }
}
