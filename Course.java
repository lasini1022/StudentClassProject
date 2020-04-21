import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Course {
    private int numCoursesEnrolled = 0;
    private ArrayList<ArrayList<String>> teacher_list = new ArrayList<>();

    /**
     * checks if there is space left in the array for it to be added.
     * It checks if the number of enrolled courses for the student has already exceeded the maximum number of courses.
     * @return true if there is space left and false otherwise.
     */
    public boolean addCourses(){
        int MAX_COURSES = 4;
        if (numCoursesEnrolled < MAX_COURSES) {
                //if(courseList(cour) && check_course_format(cour)) {
                    numCoursesEnrolled++;
                    return true;
                //}
            }
        return false;
    }

    /**
     * Checks the format of the course but because this method caused problems when trying to add the course to the arryalist, it was not used.
     * @param c The course code
     * @returntrue if it is in the right format and false otherwise
     */
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

    /**
     * Reads through the course list and sees if the course entered by the user is in the course list.
     * @param cou The course entered by the user
     * @return true if the course is in the course list and false otherwise.
     */

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
     * Extracts the information from the Teacher list using string tokenizer and each token is placed in the 2d arraylist.
     */
    public void teacherFileRead() {
        int row= 0;//countLines();
        try{
            String educators = "src/TeacherList";
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

    /**
     * Removes the course from the teacher that will be replaced and the course is then copied and is added to the replacing teacher's line.
     * @param t1 the name of the teacher that will be replaced
     * @param t2 the name of the teacher that will replace t1
     * @param cCT the course code that will be moving between the teachers
     */
    public void teacherChanger(String t1, String t2, String cCT) {//cCT means changeCourseTeacher
        String stRemoved = null;
        teacherFileRead();
        int len=13;
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

    /**
     * Prints the teacher list array list for confirmation cases.
     */
    public void teacherFilePrint (){
        int row=13;
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

}
