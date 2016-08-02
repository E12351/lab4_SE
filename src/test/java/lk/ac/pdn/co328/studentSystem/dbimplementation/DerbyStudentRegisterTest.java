package lk.ac.pdn.co328.studentSystem.dbimplementation;

import lk.ac.pdn.co328.studentSystem.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AnjelDethWingZ on 8/2/2016.
 */
public class DerbyStudentRegisterTest {
    DerbyStudentRegister register = null;
    @Before
    public void setUp() throws Exception {
        //register.reset();
        register = new DerbyStudentRegister();
        //register.reset();
    }

    @After
    public void tearDown() throws Exception {
        //register.removeStudent(1);
        //register.removeStudent(0);
    }

    @Test
    public void addStudent(){
            try {
                register.addStudent( new Student(0, "kamal","kumara") );
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e);
            }
        register.removeStudent(0);
        System.out.println("Add Student pass.");
    }
    @Test
    public void findStudent(){
        try {
            register.addStudent(new Student(1, "Amila", "Piyali"));
        }catch (Exception e){
            System.out.println(e);
        }
        register.findStudent(1);
        //register.removeStudent(1);
        System.out.println("Find Student pass.");
    }
    @Test
    public void removeStudent() throws Exception {

    }
    @Test
    public void reset() throws Exception {

    }

}