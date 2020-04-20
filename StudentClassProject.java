import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Lasini Kurukulasooriya and Varshini Ganendran
 * @since 4/18/2020
 */
public class StudentClassProject {
    static final String filename = "C:\\users\\Owner\\Desktop\\StudentNames.txt";
    static ArrayList<String> students; //hold the list of students
    static Scanner in = new Scanner(System.in);
    static final String file = "src/CourseList";

    public static void main(String[] args) throws IOException {
        System.out.println("How many students?");
        int num_students = in.nextInt()+1;
        //students = new String[num_names];//doesn't need since it is a array list
        for (int i = 0; i < num_students; i++) { //go through all students
            students.add(getUserInput());
        }
        writeFile();
        File names_file = new File(filename); // Specify the filename
        Scanner myReader = new Scanner(names_file);

        int line_num = 1;
        //read the file
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine(); //read in one line from a while
            System.out.print(line_num);
            //tokenize the line
            StringTokenizer st = new StringTokenizer(data, "|");
            System.out.println(" Name: " + st.nextToken());
            System.out.println("Student #: " + st.nextToken());
            System.out.println("DOB: " + st.nextToken());
            System.out.println("Courses: " + st.nextToken());
            line_num++;
        }
        //myReader.close();
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

                    break;
                case "done":
                    break label;
                default:
                    System.out.println("Sorry, you have not typed the proper ");
                    break;
            }
        } while (extra.equals("add") || extra.equals("delete"));

        System.out.println("Would would you want to add or delete a teacher, if not type delete");
        //System.out.println("d");
        String user = in.nextLine();
        Course d = new Course();
        do {
            System.out.println("Please type in the teacher would you like to replace: ");
            String teacher1 = in.nextLine();
            System.out.println("Please enter the name of the teacher that you would like to replace " + teacher1 + " with: ");
            String teacher2 = in.nextLine();
            d.teacherInput(teacher1, teacher2);
        }while();
    }

    private static String getUserInput() {
        System.out.println("Type student name and press enter");
        String name = in.next();
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
        System.out.println("Type how many courses does the student have then press enter: ");
        int count_stop = in.nextInt()+1;
        Course c = new Course ();
        String [] courses = new String[count_stop];
        System.out.println("These are the courses you can pick from:");
        courseList_Print();
        System.out.println("Type in course code or type done when finished: ");
        String course;
        // int counter =0;
        for(int h=0; h<courses.length;h++) {
            course = in.nextLine();
            //if(course.equals("done")){break;}
            //else if (c.courseList(course) && c.check_course_format(course)) {
                //if (c.addCourses(course).equals(course)) {
            if(c.addCourses(course)) {
                courses[h] = course;
            }
        //}
                //}
            //} //else {
                //h--;
           // }
        }
        System.out.println("The courses for this student "+c.progress()+" semester.");
        System.out.println(Arrays.toString(courses));
        Student a = new Student (name, LocalDate.of(year,month,day));
        String nom = a.getName();
        LocalDate DOB = a.getDOB();
        int id = a.getID();
        StringBuilder co= new StringBuilder();
        for (String cours : courses) {
            co.append(cours).append(",");
        }
        String s =nom+"|"+id+"|"+DOB+"|"+co;
        System.out.println(s);
        return s;//can later add student and room number
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
    public static void courseList_Print(){
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
