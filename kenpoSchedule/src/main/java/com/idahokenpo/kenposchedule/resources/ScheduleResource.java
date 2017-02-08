package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.dao.DataLoader;
import com.idahokenpo.kenposchedule.dao.InstructorDao;
import com.idahokenpo.kenposchedule.dao.StudentDao;
import com.idahokenpo.kenposchedule.dao.WeeklyScheduleDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Student;
import com.idahokenpo.kenposchedule.data.WeeklySchedule;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Korey
 */
@Path("/")
public class ScheduleResource 
{
    private static final DataLoader loader = new DataLoader();
    private static final StudentDao studentLoader = new StudentDao();
    private final Gson gson = new Gson();
    
    @GET
    @Produces("application/json")
    @Path("students")
    public Response getStudents()
    {
        List<Student> students = studentLoader.getStudents();
        return Response.ok().entity(gson.toJson(students)).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("instructors")
    public Response getInstructors()
    {
        InstructorDao instructorDao = new InstructorDao();
        List<Instructor> instructors = instructorDao.getInstructors();
        return Response.ok().header("Content-Type", "application/json").entity(gson.toJson(instructors)).build();
    }
    
    @GET
    @Produces("application/json")
    @Path("weeklySchedule")
    public Response getWeeklySchedule(@QueryParam("weeklyScheduleId") String weeklyScheduleId)
    {
        WeeklyScheduleDao dao = new WeeklyScheduleDao();
        return Response.ok().entity(gson.toJson(dao.get(weeklyScheduleId))).build();
    }
    @PUT
    @Path("weeklySchedule")
    public void createWeeklySchedule()
    {
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        
        WeeklyScheduleDao dao = new WeeklyScheduleDao();
        
    }
}
