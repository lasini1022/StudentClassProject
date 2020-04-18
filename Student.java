import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.Arrays;

public class Student {
    static private int last_id; /*keep track of last id assigned. It's SHARED by all instances of Student class*/
    static final public int MAX_COURSES = 4;
    final private int ID; /* any field that is final, *can* be made public, it's safe*/
    /*the main purpose of ID is to tell one object from another from user perspective*/
    final private String name;
    final private LocalDate DOB;
    private String[] courses = new String[MAX_COURSES]; /* should be a list of course objects*/
    private int numCoursesEnrolled = 0;

    public Student(String name, LocalDate DOB, String[] courses) {
        this.name = name;
        this.DOB = DOB;
        this.courses = courses;
        ID = last_id;
        last_id++; //increment last ID to account for creation of new student
    }

    public Student(String name, LocalDate DOB, int courses) {
        this.name = name;
        this.DOB = DOB;
        ID = last_id;
        last_id++; //increment last ID to account for creation of new student
    }

    public String[] getCourses() {
        //return courses; <-does NOT make a copy of array, this gives access to course list
        return Arrays.copyOf(courses, courses.length); //return a full copy of array of courses
    }

    public LocalDate getDOB() {
        return DOB; //dates and times are immutable in Java just like Strings, so it's ok to just return it
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String courseList() {
        return Arrays.toString(courses);
    }

    /**
     * Adds the specified course to the list of courses that student is taking
     * Proper formats can be found on Ministry of education publication
     *
     * @param course - course to be added
     * @return true if the course was successfully added, and false otherwise (if course is already on the list, or max number of courses is reached
     * @throws java.security.InvalidParameterException if course code is not properly formatted
     */
    public boolean addCourse(String course) {
        if (!check_course_format(course)) {
            throw new InvalidParameterException("Invalid course code " + course); //exceptions are actually objects as well
        }
        if (numCoursesEnrolled < MAX_COURSES) { //check if  list of courses is not full
            for (int i = 0; i < numCoursesEnrolled; i++) { // check if course is already there
                if (courses[i].equals(course)) return false;
            }
            courses[numCoursesEnrolled] = course; //add the course to the list
            numCoursesEnrolled++; //update course counter
            return true;
        }
        return false;
    }

    /*uses a file xyz.txt with list of all course*/
    private boolean check_course_format(String course) {
        return true;
    }
}
