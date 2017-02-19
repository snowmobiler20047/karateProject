/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.dao.StudentDao;
import com.idahokenpo.kenposchedule.data.Student;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import java.util.List;
import javax.ws.rs.GET;
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
    private static final StudentDao studentLoader = new StudentDao();
    private static final Gson gson = SerializationUtils.getGson();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("students")
    public Response getStudents()
    {
        List<Student> students = studentLoader.getStudents();
        return Response.ok().entity(gson.toJson(students)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("student")
    public Response getStudent(@QueryParam("studentId") String studentId)
    {
        return Response.ok(gson.toJson(studentLoader.getStudent(studentId))).build();
    }
}
