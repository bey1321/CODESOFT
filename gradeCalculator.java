import java.util.Scanner;
import java.util.ArrayList;

public class gradeCalculator {
    public static void main(String args[]){

        System.out.println("\n-----------------Welcome to Grade Calculator-----------------");

        Scanner input = new Scanner(System.in);

        ArrayList<StudentMark> studentMarks = new ArrayList<StudentMark>();
        ArrayList<String> courseNames = new ArrayList<String>();

        // Add elements one by one
        courseNames.add("Math");
        courseNames.add("English");
        courseNames.add("History");
        courseNames.add("Geography");
        courseNames.add("Art");
        courseNames.add("Physical Education");
        courseNames.add("Computer Science");

        int i = 0;

        do{
            double courseMark1 = gradeCalculator.validatePositiveDoubleInput(input, "Enter the mark of " + courseNames.get(i) + ": ");

            studentMarks.add(new StudentMark(courseNames.get(i), courseMark1));
            i++;

        }while(i < courseNames.size());

        double totalMark = 0;
        char gradeLetter;

        for(StudentMark stMark : studentMarks){
            totalMark += stMark.getCourseMark();
        }
        
        double averageMark = totalMark / studentMarks.size();

        if(averageMark >= 90){
            gradeLetter = 'A';
        }else if(averageMark >= 80){
            gradeLetter = 'B';
        }else if(averageMark >= 70){
            gradeLetter = 'C';
        }else if(averageMark >= 60){
            gradeLetter = 'D';
        }else{
            gradeLetter = 'F';
        }

        System.out.println("\nYour total mark is: " + totalMark);
        System.out.println("\nYour average mark is: " + averageMark);
        System.out.println("\nYour grade letter is: " + gradeLetter + "\n");
        System.out.println("------------------------------------------------------------\n");

        input.close();
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
    
    public static String validateStringInput(Scanner scanner, String prompt) {
		String input;
		while (true) { 
			System.out.print(prompt);
			input = scanner.nextLine().trim(); 
			if (!input.isEmpty())
				return input; 
			System.out.println("Invalid input. Please enter a valid string."); 
		}
	}
}
class StudentMark{
    private String courseName;
    private double courseMark;

    StudentMark(String courseName, double courseMark){
        this.courseName = courseName;
        this.courseMark = courseMark;
    }

    public String getCourseName(){
        return courseName;
    }

    public double getCourseMark(){
        return courseMark;
    }

    public void setCourseName(String courseName){
        this.courseName = courseName;
    }

    public void setCourseMark(double courseMark){
        this.courseMark = courseMark;
    }
}
