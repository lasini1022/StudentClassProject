import java.time.LocalDate;

public class Student {
    static private int last_id=1; /*keep track of last id assigned. It's SHARED by all instances of Student class*/
    final private int ID; /* any field that is final, *can* be made public, it's safe*/
    /*the main purpose of ID is to tell one object from another from user perspective*/
    final private String name;
    final private LocalDate DOB;

    public Student (String name, LocalDate DOB){
        this.name = name;
        this.DOB = DOB;
        ID = last_id;
        last_id++; //increment last ID to account for creation of new student
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

    public String progress(){
        LocalDate today = LocalDate.now();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        if(month>8 && month<2){
            if(month==9){//if current day is in september and is before or after day 3 of the month
                if(day>2){
                    return "present";
                }
            }
            if(month==1){
                if(day==30){return "present";}
                else if(day==31){return "future";}
                else if(day<30){return "present";}
            }
            return "present";
        }
        else if(month>1 && month<7){
            if(month==6){
                if(day>24 && day<30){
                    return "past";
                }
                else if(day<25){
                    return "present";
                }
            }
        }
        else if(month>5 && month<9){
            return "past";
        }
        return "none";
    }
}
