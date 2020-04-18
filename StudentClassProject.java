import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.StringTokenizer;


public class VarshiniTestStudentClassProject {
    private static final String filename = "names.txt";
    private static Student[] students; //hold the list of students
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        try {
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

        } catch (FileNotFoundException e) {
            //file is not there, so create it

            System.out.println("How many students?");
            int num_names = in.nextInt();
            students = new Student[num_names];

            for (int i = 0; i < num_names; i++) { //go through all students
                students[i] = getUserInput();
            }
            writeFile();

        }

    }




    private static Student getUserInput() throws IOException {
        System.out.println("Type student name and press enter");
       // String input = in.next() + "|";
        Student s;
        //System.out.println("Type student number and press enter");
        String name = in.next();

        System.out.println("Type how many courses does a student have and press enter");
        int courses = in.nextInt();

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
            System.out.println("Type the date of birth");
            day = in.nextInt();
        } while (local(year,month,day));

        System.out.println("Type in course code, or type done if finished");
        for (int i = 0; i < 4; i++) { //ask for 4 courses

            String course = in.next();
            if (course.equals("done")) break;
            //input += course + ",";
        }
        s = new Student (name, LocalDate.of(year,month,day), courses);
        return s;
    }

    private static void writeFile() {
        try {
            FileWriter myWriter = new FileWriter(filename);

            for (int i = 0; i < students.length; i++) {
                myWriter.write(students[i] + "\r\n");
            }
            myWriter.close();
        }
        catch(IOException error){
            System.out.println("error occurred creating a file");
        }

    }

    private static boolean local (int year, int month, int day) throws IOException {
        LocalDate date = LocalDate.of(year, month, day);
        return false;
    }
}

