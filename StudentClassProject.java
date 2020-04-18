import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StudentClassProject {
    private static final String filename = "C:\\users\\Owner\\Desktop\\StudentNames.txt";
    static String [] students; //hold the list of students
    private static Scanner in = new Scanner(System.in);
    static ArrayList<String> courses = null;

    public static void main(String[] args) {
        try {
            FileWriter names_file = new FileWriter(filename); // Specify the filename
            Scanner myReader = new Scanner((Readable) names_file);

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

        } catch (FileNotFoundException e) {
            //file is not there, so create it

            System.out.println("How many students?");
            int num_names = in.nextInt();
            students = new String[num_names];
            for (int i = 0; i < num_names; i++) { //go through all students
                students = getUserInput();
            }
            writeFile();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




    private static ArrayList<String> getUserInput() throws IOException {
        System.out.println("Type student name and press enter");
        //String input = in.next() + "|";
        Student s;
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
        do {
            System.out.println("Type day of birth");
            day = in.nextInt();
        }while(local(year,month,day));
        System.out.println("Type in course code, or type done if finished");
        for (int i = 0; i < n; i++) { //ask for 4 courses
            String course = in.nextLine();
            if (course.equals("done")) break;
            courses.add(i,course);
            //input += course + ",";
        }
        s = new Student (name, LocalDate.of(year,month,day),courses);

        return "l";
    }

    /**
     * Checks if the year, month, and day entered is valid.
     * @param year year user enters for DOB
     * @param month month user enters for DOB
     * @param day day user enters for DOB
     * @return false if the three variables were able to make a
     * @throws IOException if date is not valid
     */
    private static boolean local (int year, int month, int day) throws IOException {
        LocalDate date = LocalDate.of(year,month,day);
        return false;
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

}
