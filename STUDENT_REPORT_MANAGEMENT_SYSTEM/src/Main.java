
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    public static String[] data=new String[1500];
    public static int COUNT=0;
    public static void SimpleText(){
        System.out.println("1.Add a new student record (Name, ID, Marks, Grade)");
        System.out.println("2.View all records");
        System.out.println("3.Search for a student by ID or name");
        System.out.println("4.Update a student’s marks");
        System.out.println("5.Delete a student record");
        System.out.println("6.Show top 3 students by marks");
        System.out.println();
    }
    public static void StoreData() throws IOException{
        FileReader fr=new FileReader("STUDENTS_DATA.txt");
        Scanner sc=new Scanner(fr);
        while(sc.hasNextLine()){
            String Data=sc.nextLine();
            data[COUNT]=Data;
            COUNT++;
        }
        fr.close();
        sc.close();
    }

    public static void WriteData() throws IOException{
        FileWriter fw=new FileWriter("STUDENTS_DATA.txt");
        for (String datum : data) {
            fw.write(datum);
        }
        fw.close();
    }

    public static void AddData() throws IOException {
        System.out.println("Enter the student ID:");
        Scanner sc=new Scanner(System.in);
        String ID=sc.nextLine();
        System.out.println("Enter the Student Name:");
        String Name=sc.nextLine();
        System.out.println("Enter the Marks:");
        String Marks=sc.nextLine();
        System.out.println("Enter the Student Grades:");
        String Grades=sc.nextLine();
        FileWriter fw=new FileWriter("STUDENTS_DATA.txt",true);
        String Data=ID+","+Name+","+Marks+","+Grades;
        fw.write(Data+"\n");
        StoreData();
        fw.close();
    }
    public static void ViewData() throws IOException{
        FileReader fr=new FileReader("STUDENTS_DATA.txt");
        Scanner sc=new Scanner(fr);
        while(sc.hasNextLine()){
            String Data=sc.nextLine();
            System.out.println(Data);
        }
        fr.close();
        sc.close();
    }
    public static void SearchStudentData() throws IOException{
        Scanner sc=new Scanner(System.in);
        System.out.println("Press 1 to enter id or press 0 to enter name:");
        int press=sc.nextInt();
        String ID="";
        String Name="";
        if(press==1){
            System.out.println("Enter the student ID:");
             ID=sc.nextLine();
        }
        if(press==2){
            System.out.println("Enter the Student Name:");
             Name=sc.nextLine();
        }
        if(Name.equals(" ")||ID.equals(" ")){
            System.out.println("Please enter again!");
            System.out.println("Press 1 to enter id or press 0 to enter name:");
            int Press=sc.nextInt();
            if(Press==1){
                System.out.println("Enter the student ID:");
                ID=sc.nextLine();
            }
            if(Press==2){
                System.out.println("Enter the Student Name:");
                Name=sc.nextLine();
            }
        }
        //track col
        int trackCol=0;
        FileReader fr=new FileReader("STUDENTS_DATA.txt");
        Scanner SC=new Scanner(fr);
        while(SC.hasNextLine()){
            trackCol++;
            String data=SC.nextLine();
            if(data.contains(Name)){
                System.out.println("The "+Name+" of this student Data is "+data+" and found at col "+trackCol);
            }
            if(data.contains(ID)){
                System.out.println("The "+ID+" of this student Data is "+data+" and found at col "+trackCol);
            }
        }
        fr.close();
        SC.close();
    }

    public static void UpdateStudentMarks() throws IOException{
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the Student Name:");
        String Name=sc.nextLine();
        System.out.println("Enter the Student ID:");
        String ID=sc.nextLine();
        //another scanner class
        FileReader fr=new FileReader("STUDENTS_DATA.txt");
        Scanner SC=new Scanner(fr);
        while (SC.hasNextLine()){
            String[] line=SC.nextLine().split(",");
            if(SC.nextLine().contains(Name)&&SC.nextLine().contains(ID)){
                System.out.println("Enter the new Marks:");
                String marks=sc.nextLine();
                System.out.println("Enter the Grades:");
                String Grades=sc.nextLine();
                line[2]=marks;
                line[3]=Grades;
            }
        }
        fr.close();
        SC.close();
    }

    public static void DeleteStudentRecord() throws IOException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the student Name:");
        String Name=sc.nextLine();
        System.out.println("Enter the Student ID:");
        String ID=sc.nextLine();
        //file reader declaration
        FileReader fr=new FileReader("STUDENTS_DATA.txt");
        Scanner SC=new Scanner(fr);
        int count=0;
        while (SC.hasNextLine()){
            String Data=SC.nextLine();
            if(Data.contains(Name)&&Data.contains(ID)){
                StoreData();
                data[count]="";
                WriteData();
            }
            count++;
        }
        SC.close();
        fr.close();
    }
    public static void TopThreeStudents() throws IOException{
        //first student with the highest marks
        int FirstLargest=0;
        String Marks1="";
        String Student="";
        FileReader fr1=new FileReader("STUDENTS_DATA.txt");
        Scanner sc1=new Scanner(fr1);
        while(sc1.hasNextLine()){
            String text=sc1.nextLine();
            String[] split=text.split(",");
            if(Integer.parseInt(split[2])>FirstLargest){
                FirstLargest=Integer.parseInt(split[2]);
                Marks1=split[2];
                Student=split[1];
            }
        }
        System.out.println("The top 1 student is : "+Student+" and the mark of this Student is : "+Marks1);
        sc1.close();
        fr1.close();

        //Second student with Second-highest marks
        int SecondLargest=0;
        String Marks2="";
        String Student2="";
        FileReader fr2=new FileReader("STUDENTS_DATA.txt");
        Scanner sc2=new Scanner(fr2);
        while(sc2.hasNextLine()){
            String text=sc2.nextLine();
            String[] split=text.split(",");
            if(Integer.parseInt(split[2])>SecondLargest&&FirstLargest<Integer.parseInt(split[2])){
                SecondLargest=Integer.parseInt(split[2]);
                Marks2=split[2];
                Student2=split[1];
            }
        }
        System.out.println("The top 2 student is : "+Student2+" and the mark of this Student is : "+Marks2);
        sc2.close();
        fr2.close();

        //third student with third-highest mark marks
        int ThirdLargest=0;
        String Marks3="";
        String Student3="";
        FileReader fr3=new FileReader("STUDENTS_DATA.txt");
        Scanner sc3=new Scanner(fr3);
        while(sc3.hasNextLine()){
            String text=sc3.nextLine();
            String[] split=text.split(",");
            if(Integer.parseInt(split[2])>ThirdLargest&&SecondLargest<Integer.parseInt(split[2])){
                ThirdLargest=Integer.parseInt(split[2]);
                Marks3=split[2];
                Student3=split[1];
            }
        }
        System.out.println("The top 3 student is : "+Student3+" and the mark of this Student is : "+Marks3);
        sc3.close();
        fr3.close();
    }
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        SimpleText();
        System.out.println("Enter your option:");
        int option=sc.nextInt();
        int Continue;
        do{
            switch (option){
                case 1:
                    AddData();
                    break;
                case 2:
                    ViewData();
                    break;
                case 3:
                    SearchStudentData();
                    break;
                case 4:
                    UpdateStudentMarks();
                    break;
                case 5:
                    DeleteStudentRecord();
                    break;
                case 6:
                    TopThreeStudents();
                    break;
                default:
                    System.out.println("You entered an wrong number!");
            }
            System.out.println("Press 1 to continue and press 0 to stop!");
            Continue=sc.nextInt();
        }while (Continue==1);
    }
}




