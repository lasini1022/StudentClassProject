import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Lasini Kurukulasooriya and Varshini Ganendran
 * @since 4/18/2020
 */
public class StudentClassProject {
    private static final String filename = "C:\\users\\Owner\\Desktop\\StudentNames.txt";
    static ArrayList<String> students; //hold the list of students
    private static Scanner in = new Scanner(System.in);
    static ArrayList<String> courses = null;
    static final String file = "src/CourseList";

    public static void main(String[] args) throws IOException {
        System.out.println("How many students?");
        int num_names = in.nextInt();
        //students = new String[num_names];//doesn't need since it is a array list
        for (int i = 0; i < num_names; i++) { //go through all students
            students.add(getUserInput());
        }
        writeFile();
        File names_file = new File(filename); // Specify the filename
        Scanner myReader = new Scanner(names_file);

        int line_num = 1;
        //read the file
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine(); //read in one line from a while
            System.out.print (line_num);
            //tokenize the line
            StringTokenizer st = new StringTokenizer(data, "|");
            System.out.println (" Name: " + st.nextToken());
            System.out.println ("Student #: " + st.nextToken());
            System.out.println ("DOB: " + st.nextToken());
            System.out.println ("Courses: " + st.nextToken());
            line_num++;
        }
        myReader.close();
        //asking for deleting and adding new students
        System.out.println("Would you want to add or delete student, if not press done? \n Note:Type in only lowercase");
        System.out.print("type add/delete/done: ");
        String extra = in.nextLine();

        label:
        do {
            switch (extra) {
                case "add":
                    getUserInput();
                     break;
                case "delete":
                    System.out.println("Please type in the name of the student that you will like to delete? ");
                    String deleteStudent = in.nextLine();
                    for(int i=0;i<students.size();i++) {
                        String st = students.get(i);
                        StringTokenizer tokenizer = new StringTokenizer(st, "|");
                        String token = tokenizer.nextToken();
                        if (deleteStudent.equals(token)) {
                            students.remove(i);
                        }
                    }
                    System.out.println("So this is the new list of students name");

                    for(int j=0; j<students.size();j++){
                        System.out.println(students.get(j));
                    }

                    break;
                case "done":
                    break label;
                default:
                    System.out.println("Sorry, you have not typed ");
                    break;
            }
        }

        while (extra.equals("add") || extra.equals("delete"));

    }

    private static String getUserInput() {
        System.out.println("Type student name and press enter");
        //String input = in.next() + "|";
        //Student s = null;
        //System.out.println("Type student number and press enter");
        String name = in.next();
        System.out.println("Type how may courses does a student have and press enter: ");
        int n = in.nextInt();
        int year;
        do {
            System.out.println("Type year of birth");
            year = in.nextInt();
        } while (year < 1970 || year > 2005);

        int month;
        do {
            System.out.println("Type month of birth");
            month = in.nextInt();
        }while (month < 1 || month > 12);

        int day;
        System.out.println("Type day of birth");
        day = in.nextInt();
        assert false;
        courseList();
        System.out.println("Type in course code, or type done if finished");
        for (int i = 0; i < n; i++) { //ask for 4 courses
            String course = in.nextLine();
            Course c = new Course (n, course, courses);

            // if (course.equals("done")) break;
            if(c.addCourse()) {
                courses.add(course);
            }
            //input += course + ",";
        }
        Student a = new Student (name, LocalDate.of(year,month,day));
        String nom = a.getName();
        LocalDate DOB = a.getDOB();
        int id = a.getID();
        StringBuilder co= null;
        for (String cours : courses) {
            co.append(cours).append(",");
        }
        return nom+"|"+id+"|"+DOB+"|"+co;//can later add student and room number
    }

    private static void writeFile() {

        try {
            FileWriter myWriter = new FileWriter(filename);

            for (String student : students) {
                myWriter.write(student + "\r\n");
            }
            myWriter.close();
        }
        catch(IOException error){
            System.out.println("error occurred creating a file");
        }


    }
    public static void courseList(){
        String data;
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}

