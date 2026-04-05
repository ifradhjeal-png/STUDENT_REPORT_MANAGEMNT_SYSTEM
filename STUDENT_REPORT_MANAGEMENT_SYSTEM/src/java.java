import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class java {
    static Scanner sc = new Scanner(System.in);
    static String[] data = new String[500];

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Student Data");
            System.out.println("2. Search Student Data");
            System.out.println("3. Update Student Marks");
            System.out.println("4. Delete Student Record");
            System.out.println("5. Top Three Students");
            System.out.println("6. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                AddStudentData();
            } else if (choice == 2) {
                SearchStudentData();
            } else if (choice == 3) {
                UpdateStudentMarks();
            } else if (choice == 4) {
                DeleteStudentRecord();
            } else if (choice == 5) {
                TopThreeStudents();
            } else if (choice == 6) {
                break;
            }
        }
    }

    public static void AddStudentData() {
        try {
            FileWriter fw = new FileWriter("studentdata.txt", true);
            System.out.println("Enter the student name:");
            String Name = sc.nextLine();
            System.out.println("Enter the student ID:");
            String ID = sc.nextLine();
            System.out.println("Enter the student Marks:");
            String Marks = sc.nextLine();
            System.out.println("Enter the student Grades:");
            String Grades = sc.nextLine();
            fw.write(Name + "," + ID + "," + Marks + "," + Grades + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void SearchStudentData() {
        try {
            File file = new File("studentdata.txt");
            Scanner SC = new Scanner(file);
            System.out.println("Press 1 to enter id or press 0 to enter name:");
            int press = sc.nextInt();
            sc.nextLine();
            String ID = "";
            String Name = "";
            if (press == 1) {
                System.out.println("Enter the student ID:");
                ID = sc.nextLine();
            }
            if (press == 2) {
                System.out.println("Enter the Student Name:");
                Name = sc.nextLine();
            }
            if (Name.isEmpty() || ID.isEmpty()) {
                System.out.println("Please enter again!");
                System.out.println("Press 1 to enter id or press 0 to enter name:");
                int Press = sc.nextInt();
                sc.nextLine();
                if (Press == 1) {
                    System.out.println("Enter the student ID:");
                    ID = sc.nextLine();
                }
                if (Press == 2) {
                    System.out.println("Enter the Student Name:");
                    Name = sc.nextLine();
                }
            }
            while (SC.hasNextLine()) {
                String Line = SC.nextLine();
                String[] line = Line.split(",");
                if (Line.contains(Name) || Line.contains(ID)) {
                    System.out.println("Student Name is " + line[0]);
                    System.out.println("Student ID is " + line[1]);
                    System.out.println("Student Marks are " + line[2]);
                    System.out.println("Student Grades are " + line[3]);
                }
            }
            SC.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void UpdateStudentMarks() {
        try {
            StoreData();
            File file = new File("studentdata.txt");
            Scanner SC = new Scanner(file);
            System.out.println("Enter the Name to update:");
            String Name = sc.nextLine();
            System.out.println("Enter the ID to update:");
            String ID = sc.nextLine();
            int count = 0;
            while (SC.hasNextLine()) {
                String Line = SC.nextLine();
                String[] line = Line.split(",");
                if (Line.contains(Name) && Line.contains(ID)) {
                    System.out.println("Enter the new Marks:");
                    String marks = sc.nextLine();
                    System.out.println("Enter the Grades:");
                    String Grades = sc.nextLine();
                    line[2] = marks;
                    line[3] = Grades;
                    data[count] = line[0] + "," + line[1] + "," + line[2] + "," + line[3];
                }
                count++;
            }
            SC.close();
            WriteData();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void DeleteStudentRecord() {
        try {
            StoreData();
            File file = new File("studentdata.txt");
            Scanner SC = new Scanner(file);
            System.out.println("Enter the Name to delete:");
            String Name = sc.nextLine();
            System.out.println("Enter the ID to delete:");
            String ID = sc.nextLine();
            int count = 0;
            while (SC.hasNextLine()) {
                String Data = SC.nextLine();
                if (Data.contains(Name) && Data.contains(ID)) {
                    data[count] = "";
                }
                count++;
            }
            SC.close();
            WriteData();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void TopThreeStudents() {
        try {
            File file = new File("studentdata.txt");
            Scanner SC = new Scanner(file);
            int FirstLargest = Integer.MIN_VALUE;
            int SecondLargest = Integer.MIN_VALUE;
            int ThirdLargest = Integer.MIN_VALUE;
            String FirstStudent = "";
            String SecondStudent = "";
            String ThirdStudent = "";
            while (SC.hasNextLine()) {
                String Line = SC.nextLine();
                String[] split = Line.split(",");
                if (Integer.parseInt(split[2]) > FirstLargest) {
                    ThirdLargest = SecondLargest;
                    ThirdStudent = SecondStudent;
                    SecondLargest = FirstLargest;
                    SecondStudent = FirstStudent;
                    FirstLargest = Integer.parseInt(split[2]);
                    FirstStudent = split[0];
                } else if (Integer.parseInt(split[2]) > SecondLargest && Integer.parseInt(split[2]) < FirstLargest) {
                    ThirdLargest = SecondLargest;
                    ThirdStudent = SecondStudent;
                    SecondLargest = Integer.parseInt(split[2]);
                    SecondStudent = split[0];
                } else if (Integer.parseInt(split[2]) > ThirdLargest && Integer.parseInt(split[2]) < SecondLargest) {
                    ThirdLargest = Integer.parseInt(split[2]);
                    ThirdStudent = split[0];
                }
            }
            SC.close();
            System.out.println("Top 1 Student: " + FirstStudent);
            System.out.println("Top 2 Student: " + SecondStudent);
            System.out.println("Top 3 Student: " + ThirdStudent);
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void StoreData() {
        try {
            File file = new File("studentdata.txt");
            Scanner SC = new Scanner(file);
            int count = 0;
            while (SC.hasNextLine()) {
                data[count] = SC.nextLine();
                count++;
            }
            SC.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public static void WriteData() {
        try {
            FileWriter fw = new FileWriter("studentdata.txt");
            for (int i = 0; i < data.length; i++) {
                if (data[i] != null && !data[i].equals("")) {
                    fw.write(data[i] + "\n");
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
