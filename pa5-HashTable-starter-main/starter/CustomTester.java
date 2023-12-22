/**
 * Name: Michael Brown
 * Email: mibrown@ucsd.edu
 * PID: A17037478
 * Sources Used: JDK 17 Docs
 *
 * This file contains all the hidden tests as mentioned on Git.
 */

 import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
 
 import static org.junit.Assert.*;
 
 /**
  * The custom tester for PA5, which covers required test cases.
  */
 public class CustomTester {
    
     /* 
      * Tests on student equals one with contrasting type
     */
 
     @Test
     public void testStudentEquals() {
        Student student1 = new Student("Test", "Student","A12345678");
        String student = "students";
        assertEquals(false, student1.equals(student));
     }

     /* 
      * Tests on comparing two students
     */
 
     @Test
     public void testStudentCompareTo() {
        Student student1 = new Student("Joe", "Bob","A1234");
        Student student2 = new Student("Joe", "Bob","B2134");
        assertEquals(-1, student1.compareTo(student2));
     }
 

     /* 
      * Tests on dropping a course
     */

     @Test
     public void testCourseDrop() {
      Course course = new Course("Math", "12", "Data", 100);
      Student stu2 = null;
      assertThrows(IllegalArgumentException.class, () -> {
         course.enroll(stu2);
      });

        Student student1 = new Student("Lo", "Do","A1236");
        Course course1 = new Course("CSE", "12", "Data Structure", 2);
        assertTrue(course1.enroll(student1));
        assertTrue(course1.enrolled.contains(student1));
        Student student2 = new Student("Joe", "Bob","A1234");
        assertEquals(false, course1.drop(student2));
        assertFalse(course1.enrolled.contains(student2));
     }

     /* 
      * Tests on enrolling in a course
     */
 
     @Test
     public void testCourseEnroll() {
      Course course = new Course("CSE", "12", "Data Structure", 100);
      Student stu2 = null;
      assertThrows(IllegalArgumentException.class, () -> {
         course.enroll(stu2);
      });

        Student student1 = new Student("Lo", "Do","A1236");
        Course course1 = new Course("CSE", "12", "Data Structure", 1);
        assertTrue(course1.enroll(student1));
        assertTrue(course1.enrolled.contains(student1));
        Student student2 = new Student("Joe", "Bob","A1234");
        assertEquals(false, course1.enroll(student2));
     }
 
    /* 
      * Tests on getting roster by creating and populating course
      as well as making an ArrayList to compare it too
     */

     @Test
     public void testCourseGetRoster() {
        Course course1 = new Course("CSE", "12", "Data Structure", 100);
        for (int i = 0; i < 100; i++) {
            course1.enrolled.add(new Student("Joe" + i, "Bob", "A123"));
        }
        ArrayList<Student> roster1 = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            roster1.add(new Student("Joe" + i, "Bob", "A123"));

        }
        Collections.sort(roster1);
        assertEquals(roster1, course1.getRoster());
     }
     /* 
      * Tests on constructing the sanctuary
     */
 
     @Test
     public void testSanctConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Sanctuary(-1, 10);
        });
     }
     
     /* 
      * Tests on rescuing some of animals
     */

     @Test
     public void testSanctRescuePartial() {
        Sanctuary sanct = new Sanctuary(75, 5);
        sanct.sanctuary.put("Gorrilla", 40);
        sanct.sanctuary.put("Dog", 5);

        sanct.rescue("Dog", 10);
        assertTrue(sanct.sanctuary.containsKey("Dog"));
        assertEquals(15, (int) sanct.sanctuary.get("Dog"));
        assertEquals(55, sanct.getTotalAnimals());
        assertEquals(2, sanct.sanctuary.size());

        Sanctuary sanct2 = new Sanctuary(100, 5);
        assertEquals(0,sanct2.rescue("Jaguar", 6));
        assertTrue(sanct2.sanctuary.containsKey("Jaguar"));
        assertEquals(6, (int) sanct2.sanctuary.get("Jaguar"));
        assertEquals(6, sanct2.getTotalAnimals());
        assertEquals(1, sanct2.sanctuary.size());

        assertThrows(IllegalArgumentException.class, () -> {
         sanct2.rescue("Jaguar", -66);
        });
     }
     /* 
      * Tests sanctuary rescue method under MaxSpecies
     */


     @Test
     public void testSanctRescueMaxSpecies() {
        Sanctuary sanct = new Sanctuary(50, 3);
        sanct.sanctuary.put("Gorrilla", 40);
        sanct.sanctuary.put("Dog", 5);

        assertEquals(1, sanct.rescue("Panda", 6));
        assertTrue(sanct.sanctuary.containsKey("Panda"));
        assertEquals(50, sanct.getTotalAnimals() );
      
        Sanctuary sanct2 = new Sanctuary(100, 3);
        sanct2.sanctuary.put("zebra", 80);
        sanct2.sanctuary.put("cat", 10);

        assertEquals(10, sanct2.rescue("Tiger", 20));
        assertTrue(sanct2.sanctuary.containsKey("Tiger"));
        assertEquals(100, sanct2.getTotalAnimals() );
     }
 
    /* 
      * Tests on releasing some of animals
     */

     @Test
     public void testSanctReleasePartial() {
      Sanctuary sanct2 = new Sanctuary(50, 5);
      sanct2.sanctuary.put("Gorrilla", 40);
      assertThrows(IllegalArgumentException.class, () -> {
         sanct2.release("Gorrilla", 50);
      });

        Sanctuary sanct = new Sanctuary(50, 5);
        sanct.sanctuary.put("Gorrilla", 40);
        sanct.sanctuary.put("Dog", 5);

        sanct.release("Gorrilla", 20);
        assertTrue(sanct.sanctuary.containsKey("Gorrilla"));
        assertEquals(20, (int) sanct.sanctuary.get("Gorrilla"));
        assertTrue(sanct.sanctuary.containsKey("Dog"));
        assertEquals(5, (int) sanct.sanctuary.get("Dog"));

        assertEquals(2, sanct.sanctuary.size());
     }
    /* 
      * Tests on releasing too many of animals
     */
     @Test
     public void testSanctReleaseTooMany() {
        Sanctuary sanct = new Sanctuary(50, 5);
        sanct.sanctuary.put("Gorrilla", 40);
        sanct.sanctuary.put("Dog", 5);

        assertTrue(sanct.sanctuary.containsKey("Gorrilla"));
        assertThrows(IllegalArgumentException.class, () -> {
            sanct.release("Gorrilla", 50);
        });
     }
 
 }
 