
package lk.ac.pdn.co328.restapi;
import lk.ac.pdn.co328.studentSystem.Student;
import lk.ac.pdn.co328.studentSystem.StudentRegister;
import lk.ac.pdn.co328.studentSystem.dbimplementation.DerbyStudentRegister;
import org.jboss.resteasy.util.HttpResponseCodes;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lk.ac.pdn.co328.studentSystem.arraylistimplementation.ArraylistStudentRegister;

import java.sql.SQLException;

@Path("rest")
public class StudentService{
    //private static StudentRegister register = new ArraylistStudentRegister();
    DerbyStudentRegister register = null;
    StudentService(){
        try {
            register = new DerbyStudentRegister();
            System.out.println("Constructor is Done.");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e);
        }

    }
    @GET
    @Path("student/{id}")
    // Uncommenting this will let the reciver know that you are sending a json
    @Produces( MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML )
    public Response viewStudent(@PathParam("id") int id) {
        System.out.println("Get");
        Student st=null;
        try {
            st = register.findStudent(id);
            System.out.println("Get Complete    :" + st.getFirstName() + "   "+st.getLastName());
        }catch (Exception e){
            System.out.println(e);
        }
        if(st == null){
            System.out.println("ERROR.");
            return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
        }
        //System.out.println("GOT"+st.getId()+"   "+st.getFirstName()+"  "+st.getLastName());
        System.out.println("Get End.");
        return Response.status(HttpResponseCodes.SC_OK).entity(st).build();
    }

    @PUT
    @Path("student/{id}")
    //@Consumes("application/xml")
    @Consumes(MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML)
    public Response modifyStudent(@PathParam("id") int id, Student input){
        if(input == null) {
            try {
                register.addStudent(input);
                System.out.println("Student modified.");
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e);
                return Response.status(HttpResponseCodes.SC_INTERNAL_SERVER_ERROR).build();
            }
        }
        else{
            register.removeStudent(id);
            try {
                register.addStudent(input);
                System.out.println("Student modified ");
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e);
                return Response.status(HttpResponseCodes.SC_FOUND).entity("Error.Student is modified.").build();
            }
        }
        return Response.status(HttpResponseCodes.SC_OK).build();
    }

    @DELETE
    @Path("student/{id}")

    public Response deleteStudent(@PathParam("id") int id) {
        if ((register.findStudent(id) != (null))) {
            try {
                register.removeStudent(id);

               return Response.status(HttpResponseCodes.SC_OK).build();
            } catch (Exception e) {
                return Response.status(HttpResponseCodes.SC_INTERNAL_SERVER_ERROR).build();
            }
        }else {
            return Response.status(HttpResponseCodes.SC_NOT_FOUND).build();
        }
    }

    @POST
    @Path("student/new")
    @Consumes(MediaType.APPLICATION_JSON + "," + MediaType.APPLICATION_XML)
    public Response addStudent(Student input) {
        System.out.println("Posting :"+input.getFirstName()+"   "+input.getLastName()+" "+input.getId());

        if (input != (null)) {
            try {
                register.addStudent(input);
                System.out.println("Student Added.");
                return Response.status(HttpResponseCodes.SC_OK).build();
            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(e);
                return Response.status(HttpResponseCodes.SC_BAD_REQUEST).build();
            }
        }else{
            return Response.status(HttpResponseCodes.SC_BAD_REQUEST).build();
        }

    }
}