/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.dao.InstructorDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.LessonCost;
import com.idahokenpo.kenposchedule.data.WeeklySchedule;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import io.swagger.annotations.Api;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author Korey
 */
@Path("instructor")
@Api
public class InstructorResource
{

    private static final InstructorDao instructorDao = new InstructorDao();
    Gson gson = SerializationUtils.getGson();

    @GET
    @Produces("application/json")
    @Path("instructors")
    public Response getInstructors()
    {
        List<Instructor> instructors = instructorDao.getAll();
        return Response.ok().header("Content-Type", "application/json").entity(gson.toJson(instructors)).build();
    }

    @POST
    @Produces("application/json")
    @Path("create")
    public Response createInstructor(@FormParam("prefix") String prefix,
            @FormParam("firstName") String firstName,
            @FormParam("middleName") String middleName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("address") String address,
            @FormParam("lessonCost") String lessonCost,
            @FormParam("phoneNumber") String phoneNumber)
    {
        Instructor instructor = new Instructor();
        instructor.setPrefix(prefix);
        instructor.setFirstName(firstName);
        instructor.setMiddleName(middleName);
        instructor.setLastName(lastName);
        instructor.setEmail(email);
        instructor.setAddress(address);
        instructor.setLessonCost(LessonCost.valueOf(lessonCost));
        instructor.setPermenantSchedule(new WeeklySchedule());
        instructor.setPhoneNumber(lastName);
        
        instructorDao.insert(instructor);

        return Response.ok().header("Content-Type", "application/json").entity(gson.toJson(instructor)).build();
    }
    
//    @POST
//    @Produces("application/json")
//    @Path("edit")
//    public Response editInstructor(@FormParam("instructor") Instructor instructor)
//    {
//        
//        instructorDao.update(instructor);
//
//        return Response.ok().header("Content-Type", "application/json").entity(gson.toJson(instructor)).build();
//    }
}
