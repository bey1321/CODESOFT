import java.util.Scanner;

public class numberGame {
    public static void main(String[] args){
        System.out.println("\n--------Welcome to the Number Game!!--------\n");
        Scanner input = new Scanner(System.in);

        int Attempts = 0;

        int randomNumber = (int)Math.floor(Math.random() * 100) + 1;

        System.out.print("Enter the number of attempts you want to have. (Enter 0 for infinite attempts):");  
        int numberOfAttemptsAllowed = input.nextInt();

        do{             
            System.out.print("\nEnter a number between 1 and 100: ");
            int userNumber = input.nextInt();
            Attempts++;

            if(userNumber == randomNumber){
                System.out.println("\nCongratulations!! You guessed the correct number!! You have got the answer in " + Attempts + " attempts.");            
                break;
            }else if(userNumber > randomNumber){
                System.out.println("\nThe number is smaller than " + userNumber);
            }else{  
                System.out.println("\nThe number is greater than " + userNumber);
            }


        }while(numberOfAttemptsAllowed == 0 || Attempts < numberOfAttemptsAllowed);

        if(numberOfAttemptsAllowed != 0 && Attempts == numberOfAttemptsAllowed){
            System.out.println("\nSorry!! You have exhausted all your attempts. The number was " + randomNumber);
        }

        System.out.println("\nThank you for playing the Number Game!! Have a good day!!");
        System.out.println("-----------------------------------------------------------");
    }
}
