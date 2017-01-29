package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.dao.DataLoader;
import com.idahokenpo.kenposchedule.dao.StudentDao;
import com.idahokenpo.kenposchedule.data.Student;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response getStudents()
    {
        List<Student> students = studentLoader.getStudents();
        return Response.status(Response.Status.OK).entity(gson.toJson(students)).build();
    }
    
    @GET
    @Produces("application/json")
    public Response getInstructors()
    {
        return null;
    }
}
