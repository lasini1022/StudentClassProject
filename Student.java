import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Student {
    static private int last_id=1; /*keep track of last id assigned. It's SHARED by all instances of Student class*/
    static final public int MAX_COURSES = 4;
    final private int ID; /* any field that is final, *can* be made public, it's safe*/
    /*the main purpose of ID is to tell one object from another from user perspective*/
    final private String name;
    final private LocalDate DOB;
    private ArrayList<String> courses = new ArrayList<>(MAX_COURSES); /* should be a list of course objects*/
    private int numCoursesEnrolled = 0;
    private static final String filename = "C:\\users\\Owner\\Desktop\\ClassList.txt";

    public Student (String name, LocalDate DOB, ArrayList<String> courses){
        this.name = name;
        this.DOB = DOB;
        this.courses = courses;
        ID = last_id;
        last_id++; //increment last ID to account for creation of new student
    }

    public Student (String name, LocalDate DOB){
        this.name = name;
        this.DOB = DOB;
        ID = last_id;
        last_id++; //increment last ID to account for creation of new student
    }

    public ArrayList<String> getCourses (){
        //return courses; <-does NOT make a copy of array, this gives access to course list
        return (ArrayList<String>) List.copyOf( courses );//return a full copy of array of courses
    }

    public LocalDate getDOB(){
        return DOB; //dates and times are immutable in Java just like Strings, so it's ok to just return it
    }

    public String getName (){
        return name;
    }

    public int getID(){
        return ID;
    }

    /**
     * Adds the specified course to the list of courses that student is taking
     * Proper formats can be found on Ministry of education publication
     * @param course - course to be added
     * @return true if the course was successfully added, and false otherwise (if course is already on the list, or max number of courses is reached
     * @throws java.security.InvalidParameterException if course code is not properly formatted
     */
    public boolean addCourse (String course){
        if (!check_course_format(course) ) {
            throw new InvalidParameterException("Invalid course code " + course); //exceptions are actually objects as well
        }
        if (numCoursesEnrolled < MAX_COURSES) { //check if  list of courses is not full
            for (int i = 0; i < numCoursesEnrolled; i++) { // check if course is already there
                if (courses.get(i).equals(course)) return false;
            }
            while () {
                courses.add(numCoursesEnrolled, course); //add the course to the list
                numCoursesEnrolled++; //update course counter
                return true;
            }
        }
        return false;
    }

    /*uses a file xyz.txt with list of all course*/
    private boolean check_course_format (String course){
        char n = course.charAt(4);
        if(course.length()==5) {
            if ((course.charAt(0)>64 && course.charAt(0)<91)) {
                if ((course.charAt(1)>64 && course.charAt(1)<91)) {
                    if ((course.charAt(2)>64 && course.charAt(2)<91)) {
                        if ((course.charAt(3) >0 && course.charAt(3)<5) || (course.charAt(3)>64 && course.charAt(3)<69)) {
                            if (n==8 || (n>66 && n<70) || (n>75 && n<81) || (n==85)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void courseList(){
        String data;
        try {
            File myObj = new File("filename.txt");
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
