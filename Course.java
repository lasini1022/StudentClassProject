import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Course {
    private int MAX_COURSES;
    private String course;
    private int numCoursesEnrolled = 0;
    private ArrayList<String> courses;
    static final String file = "src/CourseList";
    public Course(int MAX_COURSES, String course, ArrayList<String> courses){
        this.MAX_COURSES = MAX_COURSES;
        this.course = course;
        this.courses = courses;

    }
    /**
     * Adds the specified course to the list of courses that student is taking
     * Proper formats can be found on Ministry of education publication
     * @return true if the course was successfully added, and false otherwise (if course is already on the list, or max number of courses is reached
     * @throws java.security.InvalidParameterException if course code is not properly formatted
     */
    public boolean addCourse (){
        if (!check_course_format(course) ) {
            throw new InvalidParameterException("Invalid course code " + course); //exceptions are actually objects as well
        }
        if (numCoursesEnrolled < MAX_COURSES) { //check if  list of courses is not full
            for (int i = 0; i < numCoursesEnrolled; i++) { // check if course is already there
                if (courses.get(i).equals(course)) return false;
            }
            if (courseList()) {
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
                        if (course.charAt(3) == 4){
                            return n == 8 || (n > 66 && n < 70) || (n > 75 && n < 81) || (n == 85);
                        }
                    }
                }
            }
        }
        return false;
    }
    public ArrayList<String> getCourses (){
        //return courses; <-does NOT make a copy of array, this gives access to course list
        return (ArrayList<String>) List.copyOf( courses );//return a full copy of array of courses
    }

     public boolean courseList(){
        String data;
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                if (data.equals(course)) {
                    return true;
                }
                else{
                    System.out.println("You have entered an invalid course.");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
