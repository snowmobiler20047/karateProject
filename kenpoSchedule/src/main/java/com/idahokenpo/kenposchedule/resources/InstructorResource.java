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
import javax.ws.rs.core.MediaType;
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
    @Produces(MediaType.APPLICATION_JSON)
    @Path("instructors")
    public Response getInstructors()
    {
        List<Instructor> instructors = instructorDao.getAll();
        return Response.ok().entity(gson.toJson(instructors)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
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
        instructor.setPhoneNumber(phoneNumber);

        instructorDao.insert(instructor);

        return Response.ok().entity(gson.toJson(instructor)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("edit")
    public Response editInstructor(@FormParam("instructorId") String instructorId,
            @FormParam("prefix") String prefix,
            @FormParam("firstName") String firstName,
            @FormParam("middleName") String middleName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("address") String address,
            @FormParam("lessonCost") String lessonCost,
            @FormParam("phoneNumber") String phoneNumber)
    {
        Instructor instructor = instructorDao.get(instructorId);
        instructor.setPrefix(prefix);
        instructor.setFirstName(firstName);
        instructor.setMiddleName(middleName);
        instructor.setLastName(lastName);
        instructor.setEmail(email);
        instructor.setAddress(address);
        instructor.setLessonCost(LessonCost.valueOf(lessonCost));
        instructor.setPermenantSchedule(new WeeklySchedule());
        instructor.setPhoneNumber(phoneNumber);

        instructorDao.update(instructor);

        return Response.ok().entity(gson.toJson(instructor)).build();
    }
}
