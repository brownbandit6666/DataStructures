/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

import java.util.Objects;


public class Student implements Comparable<Student>{
    private final String firstName;
    private final String lastName;
    private final String PID;


    public Student(String firstName, String lastName, String PID){
        if(firstName == null || lastName == null || PID == null){
            throw new IllegalArgumentException();
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.PID = PID;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getPID(){
        return PID;
    }

    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        else if(!(o instanceof Student)){
            return false;
        }
        Student newO = (Student) o;
        return newO.firstName.equals(firstName) &&
        newO.lastName.equals(lastName) &&
        newO.PID.equals(PID);
        
    }

    public int hashCode() {
        return Objects.hash(firstName, lastName, PID);
    }

    public int compareTo(Student o){
        if(o == null){
            throw new NullPointerException();
        }
        if(getLastName().compareTo(o.getLastName()) != 0){
            return getLastName().compareTo(o.getLastName());
        }
        else if(getFirstName().compareTo(o.getFirstName()) != 0){
            return getFirstName().compareTo(o.getFirstName());
        }
        else{
            return getPID().compareTo(o.getPID());
        }
    }


}
