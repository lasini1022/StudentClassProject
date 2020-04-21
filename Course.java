import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Course {
    private int numCoursesEnrolled = 0;
    private ArrayList<ArrayList<String>> teacher_list = new ArrayList<>();
    private static String educators = "src/TeacherList";

    //public Course(){
      //  this.row=countLines();
   //}

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

    private boolean courseList(String cou){
        try {
            String file = "src/CourseList";
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.equals(cou)) {
                    return true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     */
    public void teacherFileRead() {
        int row= 0;//countLines();
        try{
            File teacherFile = new File(educators);
            Scanner myReader = new Scanner(teacherFile);
            while(row<13) {
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    StringTokenizer tokenizer = new StringTokenizer(data, "|");
                    while (tokenizer.hasMoreTokens()) {
                        String token = tokenizer.nextToken();
                        teacher_list.get(row).add(token);
                    }
                }
                row++;
            }
        }catch(FileNotFoundException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int col;
        for(int i=0; i<row; i++){
            int j=0;
            col=teacher_list.get(i).size();
            while(j<col){
                System.out.print(teacher_list.get(row).get(col)+",");
                col++;
            }
            System.out.print("\n");
        }
        teacherFilePrint();
    }

    public void teacherChanger(String t1, String t2, String cCT) {//cCT means changeCourseTeacher
        String stRemoved = null;
        teacherFileRead();
        int len=13;//countLines();
        teacherFilePrint();
        int col;
        for (int i = 0; i < len; i++) {
            int j = 0;
            col = teacher_list.get(i).size();
            while (j < col) {
                String st = teacher_list.get(i).get(j);
                if (st.equals(t1)) {
                    for (int h = 1; h < col; h++) {
                        String st1 = teacher_list.get(i).get(h);
                        if (st1.equals(cCT)) {
                            String st2 = teacher_list.get(i).get(h + 1);
                            if (st2.equals("Active")) {
                                stRemoved = teacher_list.get(i).get(h);
                                teacher_list.remove(st1);
                                teacher_list.remove(st2);
                            }
                        }
                    }
                }
                col++;
            }
        }
        for(int i=0; i<len; i++){
            int j=0;
            col=teacher_list.get(i).size();
            while(j<col){
                String st = teacher_list.get(i).get(j);
                if (st.equals(t2)) {
                    teacher_list.get(i).add(stRemoved);
                    teacher_list.get(i).add("Active");
                }
                col++;
            }
        }
    }

    public void teacherFilePrint (){
        int row=13;//countLines();
        int col;
        for(int i=0; i<row; i++){
            int j=0;
            col=teacher_list.get(i).size();
            while(j<col){
                System.out.print(teacher_list.get(row).get(col)+",");
                col++;
            }
            System.out.print("\n");
        }
    }

    public static int countLines() {
        int row=0;
        try {
            File myObj = new File(educators);
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                row++;
            }
            reader.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return 16;
    }
}

//check if arraylist is empty by y=using .empty() method
