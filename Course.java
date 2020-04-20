import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    //private String course;
    private int numCoursesEnrolled = 0;
    private Scanner in = new Scanner(System.in);
    private final String teacher = "src/TeacherList";
    private ArrayList<ArrayList<String>> teachers = new ArrayList<>(2);
    static ArrayList<String> educators = new ArrayList<>();
    static final String file = "src/CourseList";

    ////public Course(int n){
        //this.course = course;
        //this.courses = courses;

   // }
    //
    // public String addCourse1 (){
    //if (!check_course_format()) {
    //  throw new InvalidParameterException("Invalid course code " + course); //exceptions are actually objects as well
    //}
    //if (numCoursesEnrolled < MAX_COURSES) { //check if  list of courses is not full
    //  for (int i = 0; i < numCoursesEnrolled; i++) { // check if course is already there
    //    if (courses.get(i).equals(course)) return ;
    //}
    //if (courseList()) {
    ////courses.add(course); //add the course to the list
    //numCoursesEnrolled++; //update course counter
    //return course;
    //}
    //}
    //return false;
    //}

    /**
     * checks if there is any space left in the array list anymore for adding courses.
     * @return true if there is space left and false otherwise
     */
    public boolean addCourses(String cour){
        int MAX_COURSES = 4;
        if (numCoursesEnrolled < MAX_COURSES) {
                if(courseList(cour) && check_course_format(cour)) {
                    numCoursesEnrolled++;
                    return true;
                }
            }
        return false;
    }

    /*uses a file xyz.txt with list of all course*/
    public boolean check_course_format(String c) {
        char one = c.charAt(0);
        char two = c.charAt(1);
        char three = c.charAt(2);
        char fourth = c.charAt(3);
        char fifth = c.charAt(4);

        if(c.length()==5) {
            if ((one>64 && one<91)) {
                if ((two>64 && two<91)) {
                    if ((three>64 && three<91)) {
                        if (fourth == 4){
                            return (fifth == 8) || (fifth > 66 && fifth < 70) || (fifth > 75 && fifth < 81) || (fifth == 85);
                        }
                    }
                }
            }
        }
        return false;
    }
    //public ArrayList<String> getCourses (){
        //return courses; <-does NOT make a copy of array, this gives access to course list
      //  return courses ;// comment this out//return a full copy of array of courses
    //}

    private boolean courseList(String cou){
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(cou)) {
                    return true;
                }
            }
            //myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return the list of teachers after deleting it
     * */
    public String teacherInput(String t1, String t2) {

        ArrayList<String> teacher_list = new

    }
}

//check if arraylist is empty by y=using .empty() method
