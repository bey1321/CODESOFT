import java.util.Scanner;

public class AtmInterface {
    public static void main(String[] args) {
        System.out.println("\n-----------------Welcome to ATM-----------------");

        Atm atm = new Atm(100000, 10000);
        BankAccount bankAccount = new BankAccount("1234567890", "John Doe", 50000, 1234);

        Scanner input = new Scanner(System.in);
        int choice1;

        do{
            System.out.println("\nEnter your choice:");
            System.out.println("1.Login to your account");
            System.out.println("2.Exit");
            choice1 = validatePositiveIntInput(input, "Enter your choice: ");
            
            switch(choice1){
                case 1:
                    atm.validatePin(bankAccount, input);

                    int choice2;
                    do{
                        System.out.println("\nChoose the operation you want to perform:");
                        System.out.println("1. Withdraw");
                        System.out.println("2. Deposit");
                        System.out.println("3. Check Balance");
                        System.out.println("4. Exit");
                        choice2 = validatePositiveIntInput(input, "\nEnter your choice: ");

                        switch(choice2){
                            case 1:
                                atm.withdraw(bankAccount, input);
                                break;
                            case 2:
                                atm.deposit(bankAccount, input);
                                break;
                            case 3:
                                atm.checkBalance(bankAccount);
                                break;
                            case 4:
                                System.out.println("\nThank you for using our ATM!! Have a good day!!");
                                System.out.println("----------------------------------------");
                                System.out.println("----------------------------------------\n");
                                break;
                            default:
                                System.out.println("Invalid choice");
                        }
                    }while(choice2 != 4);
                    break;
                case 2:
                    System.out.println("\nThank you for using our ATM!! Have a good day!!");
                    System.out.println("----------------------------------------");
                    System.out.println("----------------------------------------\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        }while(true);
    }
    
	public static int validatePositiveIntInput(Scanner scanner, String prompt) {
		while (true) { 
			System.out.print(prompt); 
			if (scanner.hasNextInt()) { 
				int input = scanner.nextInt(); 
				scanner.nextLine(); 
				if (input > 0) 
					return input; 
			} else
				scanner.nextLine(); 
			System.out.println("Invalid input. Please enter a positive integer."); 
		}
	}

	public static double validatePositiveDoubleInput(Scanner scanner, String prompt) {
		while (true) { 
			System.out.print(prompt); 
			if (scanner.hasNextDouble()) { 
				double input = scanner.nextDouble();
				scanner.nextLine(); 
				if (input > 0)
					return input; 
			} else
				scanner.nextLine(); 
			System.out.println("Invalid input. Please enter a positive number."); 
		}
	}
    
}
class Atm {
    private int atmBalance;
    private int TransactionLimit;

    Atm(int atmBalance, int TransactionLimit){
        this.atmBalance = atmBalance;
        this.TransactionLimit = TransactionLimit;
    }

    public boolean validatePin(BankAccount bankAccount, Scanner input){
        int enteredPin;
        int counter = 0;

        do{
            enteredPin = AtmInterface.validatePositiveIntInput(input, "\nPlease enter your pin: ");
            
            if(enteredPin != bankAccount.getPin()){
                System.out.println("Incorrect pin. Please try again!!");
                counter++;
            }
            else{       
                System.out.println("\nLogin successfull!!");
                bankAccount.setSuccessfullLogin(true);
                return true;
            }
        }while(counter < 5 && enteredPin != bankAccount.getPin());
        
        if(!bankAccount.getSuccessfullLogin()){
            System.out.println("\nYou have exceeded the maximum number of attempts. Please try again later!!");
            System.out.println("\n--------------------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------------------\n");
            System.exit(0);
        }
        return false;
    }
    public void withdraw(BankAccount bankAccount, Scanner input){
        if(bankAccount.getBalance() == 0){
            System.out.println("\nYou have insufficient balance!!");
            System.out.println("\n----------------------------------------");
            return;
        }
        else{
            double amount = AtmInterface.validatePositiveDoubleInput(input, "\nEnter the amount you want to withdraw: ");
            if(amount > bankAccount.getBalance()){
                System.out.println("\nYou have insufficient balance!!");
                System.out.println("\n----------------------------------------");
                return;
            }
            else if(amount > atmBalance){
                System.out.println("\nATM has insufficient balance!!");
                System.out.println("\n----------------------------------------");
                return;
            }
            else if(amount > TransactionLimit){
                System.out.println("\nYou have exceeded the transaction limit!!");
                System.out.println("\n----------------------------------------");
                return;
            }
            else{
                bankAccount.setBalance(bankAccount.getBalance() - amount);
                atmBalance -= amount;
                System.out.println("\nAmount withdrawn successfully. Your current balance is: " + bankAccount.getBalance() + " AED");
                System.out.println("\n--------------------------------------------------------------------------------");
            }
        }
    }

    public void deposit(BankAccount bankAccount, Scanner input){
        double amount = AtmInterface.validatePositiveDoubleInput(input, "\nEnter the amount you want to deposit: ");
        
        bankAccount.setBalance(bankAccount.getBalance() + amount);
        atmBalance += amount;

        System.out.println("Amount deposited successfully. Your current balance is: " + bankAccount.getBalance() + " AED");
        System.out.println("\n----------------------------------------");

    }

    public void checkBalance(BankAccount bankAccount){
        System.out.println("Your current balance is: " + bankAccount.getBalance() + " AED");
        System.out.println("\n----------------------------------------");
    }
}
class BankAccount{
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private int pin;
    private boolean successfullLogin = false;

    BankAccount(String accountNumber, String accountHolderName, double balance, int pin){
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = pin;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public String getAccountHolderName(){
        return accountHolderName;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
    public int getPin(){
        return pin;
    }
    public void setSuccessfullLogin(boolean successfullLogin){
        this.successfullLogin = successfullLogin;
    }
    public boolean getSuccessfullLogin(){
        return successfullLogin;
    }
}
