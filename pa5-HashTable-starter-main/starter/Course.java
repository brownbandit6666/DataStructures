/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 */

import java.util.HashSet;
import java.util.Collections;
import java.util.ArrayList;


public class Course {

    HashSet<Student> enrolled = new HashSet<>();
    private final int capacity;
    private final String department;
    private final String number;
    private final String description;

    public Course(String department, String number, String description, 
    int capacity){
        if(department == null || number == null || description == null){
            throw new IllegalArgumentException();
        }
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }
        this.department = department;
        this.number = number;
        this.description = description;
        this.capacity = capacity;
    }

    public String getDepartment(){
        return department;
    }

    public String getNumber(){
        return number;
    }

    public String getDescription(){
        return description;
    }

    public int getCapacity(){
        return capacity;
    }

    public boolean enroll(Student student){
        if(student == null){
            throw new IllegalArgumentException();
        }
        if(enrolled.size() < capacity){
            return enrolled.add(student);
        }
        return false;
    }

    public boolean drop(Student student){
        if(student == null){
            throw new IllegalArgumentException();
        }
        if(enrolled.contains(student)){
            return enrolled.remove(student);
        }
        return false;
    }

    public void cancel(){
        enrolled.clear();
    }

    public boolean isFull(){
        if(enrolled.size() == capacity){
            return true;
        }
        return false;
    }

    public int getEnrolledCount(){
        return enrolled.size();
    }

    public int getAvailableSeats(){
        return capacity - enrolled.size();
    }

    public HashSet<Student> getStudents(){
        return (HashSet<Student>) enrolled.clone();
    }

    public ArrayList<Student> getRoster(){
        ArrayList<Student> newRoster = new ArrayList<>();
        for(Student student: enrolled){
            newRoster.add(student);
        }
        Collections.sort(newRoster);
        return newRoster;
    }

    public String toString(){
        return String.format("%s %s [%d] %s", department, number, 
        capacity, description);
    }
}

