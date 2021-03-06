package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.dao.StudentDao;
import com.idahokenpo.kenposchedule.data.Student;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Korey
 */
@Path("student")
public class StudentResource 
{
    private static final StudentDao studentDao = new StudentDao();
    private static final Gson gson = SerializationUtils.getGson();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("students")
    public Response getStudents()
    {
        List<Student> students = studentDao.getAll();
        return Response.ok().entity(gson.toJson(students)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("student")
    public Response getStudent(@QueryParam("studentId") String studentId)
    {
        return Response.ok(gson.toJson(studentDao.get(studentId))).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createStudent(@FormParam("prefix") String prefix,
            @FormParam("firstName") String firstName,
            @FormParam("middleName") String middleName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("address") String address,
            @FormParam("lessonCost") String lessonCost,
            @FormParam("phoneNumber") String phoneNumber,
            @FormParam("active") boolean active)
    {
        Student student = new Student(prefix, firstName, lastName);
        student.setMiddleName(middleName);
        student.setEmail(email);
        student.setAddress(address);
        student.setPhoneNumber(phoneNumber);
        student.setActive(active);

        studentDao.insert(student);

        return Response.ok().entity(gson.toJson(student)).build();
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("edit")
    public Response editStudent(@FormParam("studentId") String studentId,
            @FormParam("prefix") String prefix,
            @FormParam("firstName") String firstName,
            @FormParam("middleName") String middleName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("address") String address,
            @FormParam("lessonCost") String lessonCost,
            @FormParam("phoneNumber") String phoneNumber,
            @FormParam("active") boolean active)
    {
        Student student = studentDao.get(studentId);
        student.setPrefix(prefix);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setMiddleName(middleName);
        student.setEmail(email);
        student.setAddress(address);
        student.setPhoneNumber(phoneNumber);
        student.setActive(active);

        studentDao.update(student);

        return Response.ok().entity(gson.toJson(student)).build();
    }
}
