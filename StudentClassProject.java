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
        System.out.println("IF you would like to do anything with the student list,");
        //System.out.println("Would you want to add or delete student, if not press done? \n Note:Type in only lowercase");
        System.out.println("type any of these option: add/delete/done");
        String extraStudent = in.nextLine();

        label:
        do {
            switch (extraStudent) {
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
        } while (extraStudent.equals("add") || extraStudent.equals("delete"));
        String ans;
        do {
            System.out.print("Would would you want to change a teacher? \nType yes or no: ");
            ans = in.nextLine();
            Course d = new Course();

            System.out.println("Please type in the teacher would you like to replace: ");
            String teacher1 = in.nextLine();
            System.out.println("Please enter the name of the teacher that you would like to replace " + teacher1 + " with: ");
            String teacher2 = in.nextLine();
            d.teacherInput(teacher1, teacher2);
        } while(ans.equals("Y"));
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
        }
        System.out.println("The courses for this student "+progress()+" semester.");
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

    public static String progress(){
        //System.out.print("Enter today's year ");
        //int y  = in.nextInt();
        System.out.print("Enter today's month: ");
        int m = in.nextInt();
        System.out.print("Enter today's day: ");
        int d = in.nextInt();
        //LocalDate today = LocalDate.of(y,m,d);
        //int year = today.getYear();
        //int month = today.getMonthValue();
        //int day = today.getDayOfMonth();
        if((m>8 && m<12)  || (m<2)){
            if(m==9){//if current day is in september and is before or after day 3 of the month
                if(d>2){
                    return "are in the current";
                }
            }
            else if(m==1){
                if(d==30){return "are in the current";}
                else if(d==31){return "are in the upcoming";}
                else if(d<30){return "are in the current";}
            }
            return "are in the current";
        }
        else if(m<7){
            if(m==6){
                if(d>24 && d<30){
                    return "were in the past";
                }
                else if(d<25){
                    return "are in the current";
                }
            }
            return "are in the current";
        }
        else if(m<9){
            return "were in the past";
        }
        return "in none of the";
    }

}
