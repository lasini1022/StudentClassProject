import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Course {
    private int n;
    private int MAX_COURSES=n;
    //private String course;
    private int numCoursesEnrolled = 0;
    private ArrayList<String> courses = new ArrayList<>(n);
    static final String file = "src/CourseList";
    static Scanner in = new Scanner(System.in);

    public Course(int n){
        this.n = n;
        //this.course = course;
        //this.courses = courses;

    }
    /**
     * Adds the specified course to the list of courses that student is taking
     * Proper formats can be found on Ministry of education publication
     * @return true if the course was successfully added, and false otherwise (if course is already on the list, or max number of courses is reached
     * //@throws java.security.InvalidParameterException if course code is not properly formatted
     * @param course course that user enters
     */
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

    public String addCourses(String course){
        for(int j=0; j<MAX_COURSES; j++) {
            if (numCoursesEnrolled < MAX_COURSES) {
                //courses.add(numCoursesEnrolled, course);
                numCoursesEnrolled++;
                return course;
            }
        }
        return null;
    }

    /*uses a file xyz.txt with list of all course*/
    public boolean check_course_format(String course) {
        char one = course.charAt(0);
        char two = course.charAt(1);
        char three = course.charAt(2);
        char fourth = course.charAt(3);
        char fifth = course.charAt(4);

        if(course.length()==5) {
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
    public ArrayList<String> getCourses (){
        //return courses; <-does NOT make a copy of array, this gives access to course list
        return courses ;// comment this out//return a full copy of array of courses
    }

     public boolean courseList(String course){
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(course)) {
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
    public String progress(){
        System.out.print("Enter today's year ");
        int y  = in.nextInt();
        System.out.print("Enter today's month: ");
        int m = in.nextInt();
        System.out.print("Enter today's day: ");
        int d = in.nextInt();
        LocalDate today = LocalDate.of(y,m,d);
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        if(m>8 && m<2){
            if(m==9){//if current day is in september and is before or after day 3 of the month
                if(d>2){
                    return "present";
                }
            }
            else if(m==1){
                if(d==30){return "present";}
                else if(d==31){return "future";}
                else if(d<30){return "present";}
            }
            return "present";
        }
        else if(m>1 && m<7){
            if(m==6){
                if(d>24 && d<30){
                    return "past";
                }
                else if(d<25){
                    return "present";
                }
            }
            return "present";
        }
        else if(m>5 && m<9){
            return "past";
        }
        return "none";
    }
}
