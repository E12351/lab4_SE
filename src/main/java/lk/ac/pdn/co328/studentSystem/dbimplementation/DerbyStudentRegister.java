/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ac.pdn.co328.studentSystem.dbimplementation;

import java.sql.*;
import java.util.ArrayList;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;

/**
 *
 * @author himesh
 */
public class DerbyStudentRegister extends StudentRegister {

    Connection connection = null;
    private ArrayList<Student> studentList = new ArrayList<Student>();
    public DerbyStudentRegister() throws SQLException
    {
            String dbURL1 = "jdbc:derby:codejava/studentDB;create=true";
            connection = DriverManager.getConnection(dbURL1);
            if (connection != null)
            {
                reset();
                String SQL_CreateTable = "CREATE TABLE Students(id INT , namef VARCHAR(24),namel VARCHAR(24))";
                System.out.println ( "Creating table addresses..." );
                try 
                {
                    Statement stmnt = connection.createStatement();
                    stmnt.execute( SQL_CreateTable );
                    stmnt.close();
                    System.out.println("Table created");
                } catch ( SQLException e )
                {
                    System.out.println(e);
                }
               System.out.println("Connected to database");
            }
            else
            {
                throw new SQLException("Connection Failed");
            }
    }
    
    @Override
    public void addStudent(Student st) throws Exception {
        if (connection != null)
        {
            String SQL_AddStudent = String.format(" INSERT INTO Students VALUES (%d,'%s','%s')", st.getId(), st.getFirstName(), st.getLastName());
            System.out.println ( "Adding the student..." + SQL_AddStudent);

            try {
                Statement stmnt = connection.createStatement();
                stmnt.execute(SQL_AddStudent);
                stmnt.close();
                System.out.println("Student Added");
            }catch (Exception e){
                System.out.println(e);
            }

        }
        else
        {
            throw new Exception("Database Connection Error");
        }
    }

    @Override
    public void removeStudent(int regNo) {
        String SQL_deleteStudent = " DELETE FROM Students " + "WHERE id ="+ regNo;

        try {
            Statement stmnt = connection.createStatement();
            stmnt.execute(SQL_deleteStudent );
            stmnt.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.print(e);
        }
        System.out.println("Student Deleted.");
    }

    @Override
    public Student findStudent(int regNo) {
        Student st = null;
        if (connection != null)
        {
            String SQL_findStudent = "SELECT * FROM  Students WHERE id = "+ regNo;
            System.out.println ( "Finding the student "+ SQL_findStudent );

            Statement stmnt = null;
            try {
                stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery(SQL_findStudent);
                while (rs.next()) {
                    studentList.add(new Student(rs.getInt("id"), rs.getString("namef"), rs.getString("namel") ));
                    st = new Student(rs.getInt("id"), rs.getString("namef"), rs.getString("namel"));
                }
                //for(int i = 0; i < studentList.size(); i++) {
                //System.out.println("First name "+ st.getFirstName()+"   "+st.getLastName());
                //}
                stmnt.close();

            } catch (SQLException e) {
                System.out.println(e);
            }

            System.out.println("Student Found.");

        }
        else
        {
            System.out.println("ERROR : Something wrong with the connection.");
        }
        //st = new Student(studentList.get(0).getId(),studentList.get(0).getFirstName(),studentList.get(0).getLastName());
        return st;
    }

    @Override
    public void reset() {

        String SQL_resetStudent = "DROP TABLE Students";

        try {
            Statement stmnt = connection.createStatement();
            stmnt.execute(SQL_resetStudent );
            stmnt.close();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.print(e);
        }
        System.out.println("Student Cleared.");
    }

    @Override
    public ArrayList<Student> findStudentsByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getAllRegistrationNumbers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
