import java.util.Scanner;
import java.util.ArrayList;

public class courseRegistrationSystem {
    public static void main(String args[]){
        System.out.println("\n-----------Welcome to Course Registration System-------------");

        Scanner input = new Scanner(System.in);

        ArrayList<Course> courses = new ArrayList<Course>();

        Student student1 = new Student("John Doe", "123456", "Computer Science");
        Course course1 = new Course("Data Structures", "CS101", 3, "Dr. Smith", "Monday 10:00-12:00", "This course is about data structures", 50);
        Course course2 = new Course("Algorithms", "CS102", 3, "Dr. Johnson", "Tuesday 10:00-12:00", "This course is about algorithms", 50);
        Course course3 = new Course("Computer Networks", "CS103", 3, "Dr. Brown", "Wednesday 10:00-12:00", "This course is about computer networks", 50);
        Course course4 = new Course("Operating Systems", "CS104", 3, "Dr. White", "Thursday 10:00-12:00", "This course is about operating systems", 50);
        Course course5 = new Course("Software Engineering", "CS105", 3, "Dr. Black", "Friday 10:00-12:00", "This course is about software engineering", 50);
        Course course6 = new Course("Database Management", "CS106", 3, "Dr. Green", "Saturday 10:00-12:00", "This course is about database management systems", 50);
        Course course7 = new Course("Web Development", "CS107", 3, "Dr. Blue", "Sunday 10:00-12:00", "This course is about web development", 50);

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);

        do{
            System.out.println( "\n" + "-".repeat(60));
            System.out.println("\nEnter your choice:");
            System.out.println("1. Display all available courses");
            System.out.println("2. Register for a course");
            System.out.println("3. Drop a course");
            System.out.println("4. Display all registered courses");
            System.out.println("5. Exit");
            int choice = courseRegistrationSystem.validatePositiveIntInput(input, "Choice: ");

            switch(choice){
                case 1:
                    courseRegistrationSystem.displayAvailableCourses(courses);                        
                    break;
                case 2:
                    courseRegistrationSystem.registerForCourse(courses, student1 ,input);
                    break;
                case 3:
                    courseRegistrationSystem.DropACourse(courses, student1, input);
                    break;
                case 4: 
                    courseRegistrationSystem.DisplayRegisteredCourses(student1);
                    break;
                case 5: 
                    System.out.println("\nThank you for using Course Registration System");
                    System.out.println("--------------------------------------------------\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }while(true);      

    }    

    public static void displayAvailableCourses(ArrayList<Course> courses){
        System.out.println("\nAvailable Courses:");
        System.out.printf("%-25s %-15s %-15s %-20s %-25s %-10s %-10s%n", 
            "Course Name", "Course Code", "Course Credit", "Course Instructor", 
            "Course Schedule", "Capacity", "Availale seats");
        
        System.out.println("=".repeat(130)); // Adjusted the separator line length
        
        int i = 1;
        for (Course course : courses) {
            System.out.printf("%-25s %-15s %-15d %-20s %-25s %-10d %-10d%n", 
                i + ". " + course.courseName, 
                course.courseCode, 
                course.courseCredit, 
                course.courseInstructor, 
                course.courseSchedule, 
                course.courseCapacity, 
                course.courseCapacity - course.numberOfStudentsRegistered);
                i++;
        }
    }
    public static void registerForCourse(ArrayList<Course> courses, Student student , Scanner input){
        displayAvailableCourses(courses);
        int numberOfAvailableCourses = courses.size();
        boolean registered = false;

        do{
            int choice = courseRegistrationSystem.validatePositiveIntInput(input, "\nEnter the course you want to register (1 - " + numberOfAvailableCourses + "): ");

            if(choice < 1 || choice > numberOfAvailableCourses){
                System.out.println("\nInvalid choice");
            }else{
                Course selectedCourse = courses.get(choice - 1);

                //check if the student already registered for the course or not
                for(Course course : student.studentCourses){
                    if(course.courseName.equals(selectedCourse.courseName)){
                        System.out.println("\nYou have already registered for " + selectedCourse.courseName);
                        registered = true;
                    }
                }
                //check if the course is full or not
                if(selectedCourse.numberOfStudentsRegistered >= selectedCourse.courseCapacity){
                    System.out.println("\nCourse is full");
                }else if(registered){
                    //set registered to false for the next course registration
                    registered = false;
                }
                else{
                    selectedCourse.numberOfStudentsRegistered++;
                    student.studentCourses.add(selectedCourse);
                    System.out.println("\nYou have successfully registered for " + selectedCourse.courseName);

                }
            }            

            //ask the user whether they want to register for another course or not
            char registerAnotherCourse = courseRegistrationSystem.validateStringInput(input, "\nDo you want to register for another course? (Enter N for no): ").charAt(0);

            if(registerAnotherCourse == 'N' || registerAnotherCourse == 'n'){
                break;
            }else{
                continue;
            }

        }while(true);
    }
    public static void DisplayRegisteredCourses(Student student){
        
        int numberOfRegisterdCourses = student.studentCourses.size();
        if(numberOfRegisterdCourses == 0){
            System.out.println("\nYou have not registered for any course");
            return;
        }

        System.out.println("\nRegistered Courses:");
        System.out.printf("%-25s %-15s %-15s %-20s %-25s%n", 
            "Course Name", "Course Code", "Course Credit", "Course Instructor", 
            "Course Schedule");
        
        System.out.println("=".repeat(130)); // Adjusted the separator line length
        
        int i = 1;
        for (Course course : student.studentCourses) {
            System.out.printf("%-25s %-15s %-15d %-20s %-25s%n", 
                i + ". " + course.courseName, 
                course.courseCode, 
                course.courseCredit, 
                course.courseInstructor, 
                course.courseSchedule, 
                course.courseCapacity, 
                course.numberOfStudentsRegistered);
                i++;
        }
    }
    public static void DropACourse(ArrayList<Course> courses, Student student , Scanner input){
        
        int numberOfRegisteredCourses = student.studentCourses.size();
        // boolean registered = false;

        do{            
            DisplayRegisteredCourses(student);

            if(numberOfRegisteredCourses == 0){
                break;
            }

            int choice = courseRegistrationSystem.validatePositiveIntInput(input, "\nEnter the course you want to drop (1 - " + numberOfRegisteredCourses+ "): ");

            if(choice < 1 || choice > numberOfRegisteredCourses){
                System.out.println("\nInvalid choice");
            }else{
                Course selectedCourse = student.studentCourses.get(choice - 1);

                student.studentCourses.remove(choice - 1);
                selectedCourse.numberOfStudentsRegistered--;
                numberOfRegisteredCourses--;
                System.out.println("\nYou have successfully dropped " + selectedCourse.courseName);
            }

            //ask the user whether they want to drop another course or not
            char dropAnotherCourse = courseRegistrationSystem.validateStringInput(input, "\nDo you want to drop for another course? (Enter N for no): ").charAt(0);

            if(dropAnotherCourse == 'N' || dropAnotherCourse == 'n'){
                break;
            }else{
                continue;
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
class Course{
    String courseName;
    String courseCode;
    int courseCredit;
    String courseInstructor;
    String courseSchedule;
    String courseDescription;
    int courseCapacity;
    int numberOfStudentsRegistered = 0;

    Course(String courseName, String courseCode, int courseCredit, String courseInstructor, String courseSchedule, String courseDescription, int courseCapacity){
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.courseCredit = courseCredit;
        this.courseInstructor = courseInstructor;
        this.courseSchedule = courseSchedule;
        this.courseDescription = courseDescription;
        this.courseCapacity = courseCapacity;
    }
}
class Student{
    String studentName;
    String studentID;
    String studentMajor;
    ArrayList<Course> studentCourses;

    Student(String studentName, String studentID, String studentMajor){
        this.studentName = studentName;
        this.studentID = studentID;
        this.studentMajor = studentMajor;
        this.studentCourses = new ArrayList<Course>();
    }
}
