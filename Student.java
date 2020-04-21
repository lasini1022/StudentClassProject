import java.time.LocalDate;

public class Student {
    static private int last_id=1; /*keep track of last id assigned. It's SHARED by all instances of Student class*/
    final private int ID; /* any field that is final, *can* be made public, it's safe*/
    /*the main purpose of ID is to tell one object from another from user perspective*/
    final private String name;
    final private LocalDate DOB;
    //static Scanner in = new Scanner(System.in);

    /**
     * initializes the instance variables
     * @param name name of student
     * @param DOB the date of birth of the student
     */
    public Student (String name, LocalDate DOB){
        this.name = name;
        this.DOB = DOB;
        ID = last_id;
        last_id++; //increment last ID to account for creation of new student
    }

    /**
     *
     * @return the date of birth
     */
    public LocalDate getDOB(){
        return DOB; //dates and times are immutable in Java just like Strings, so it's ok to just return it
    }

    /**
     *
     * @return the name of the student
     */
    public String getName (){
        return name;
    }

    /**
     *
     * @return the student ID of the student
     */
    public int getID(){
        return ID;
    }


}
