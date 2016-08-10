package lk.ac.pdn.co328.restapi;

import lk.ac.pdn.co328.studentSystem.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentServiceTest {
    @Test
    public void modifyStudent() throws Exception {
        StudentService x = new StudentService();
        Student st=new Student(1,"Amila","Indrajith");
        Student st1=new Student(1,"Piyali","Gona");
        x.addStudent(st);
        x.modifyStudent(1,st1);
        x.viewStudent(1);
    }

    @Test
    public void deleteStudent() throws Exception {
        StudentService x = new StudentService();
        Student st=new Student(1,"Amila","Indrajith");
        x.addStudent(st);
        x.deleteStudent(1);
    }

    @Test
    public void viewStudent(){
        StudentService x = new StudentService();
        Student st=new Student(1,"Amila","Indrajith");
        x.addStudent(st);
        x.viewStudent(1);
    }

}