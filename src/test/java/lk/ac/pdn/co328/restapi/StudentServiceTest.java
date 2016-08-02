package lk.ac.pdn.co328.restapi;

import lk.ac.pdn.co328.studentSystem.Student;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AnjelDethWingZ on 8/2/2016.
 */
public class StudentServiceTest {
    @Test
    public void viewStudent(){
        StudentService x = new StudentService();
        Student st=new Student(1,"Amila","Indrajith");
        x.addStudent(st);
        x.viewStudent(1);
    }

}