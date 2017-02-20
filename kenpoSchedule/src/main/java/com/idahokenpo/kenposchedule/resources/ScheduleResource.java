package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.Controller;
import com.idahokenpo.kenposchedule.dao.WeeklyScheduleDao;
import com.idahokenpo.kenposchedule.data.WeekIdentifier;
import com.idahokenpo.kenposchedule.data.WeeklySchedule;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import io.swagger.annotations.Api;
import java.time.LocalDate;
import javax.inject.Singleton;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 * @author Korey
 */
@Singleton
@Path("schedule")
@Api
public class ScheduleResource 
{
    private static final WeeklyScheduleDao weeklyScheduleDao = new WeeklyScheduleDao();
    Gson gson = SerializationUtils.getGson();
    
    @GET
    @Produces("application/json")
    @Path("weeklySchedule")
    public Response getWeeklySchedule(@QueryParam("weeklyScheduleId") String weeklyScheduleId)
    {
        
        return Response.ok().entity(gson.toJson(weeklyScheduleDao.get(weeklyScheduleId))).build();
    }
    
    /**
     * This will get or create the next weekly schedule.
     * @param instructorId
     * @param weekOfYear
     * @param year
     * @param billingDateString
     * @return 
     */
    @POST
    @Path("nextWeeklySchedule")
    public Response getNextWeeklySchedule(@FormParam("instructorId") String instructorId, 
            @FormParam("weekOfYear") Integer weekOfYear,
            @FormParam("year") Integer year,
            @FormParam("billingDate") String billingDateString)
    {
        Controller controller = new Controller();
        LocalDate billingDate = LocalDate.parse(billingDateString);
        WeekIdentifier weekId = new WeekIdentifier(weekOfYear, year, billingDate);
                
        WeeklySchedule weeklySchedule = controller.getNextWeeklySchedule(instructorId, weekId);  
        return Response.ok().entity(gson.toJson(weeklySchedule)).build();
    }
    
    /**
     * 
     * @param instructorId
     * @param weekOfYear
     * @param year
     * @param billingDateString
     * @return 
     */
    @POST
    @Path("prevWeeklySchedule")
    public Response getPrevWeeklySchedule(@FormParam("instructorId") String instructorId, 
            @FormParam("weekOfYear") Integer weekOfYear,
            @FormParam("year") Integer year,
            @FormParam("billingDate") String billingDateString)
    {
        Controller controller = new Controller();
         LocalDate billingDate = LocalDate.parse(billingDateString);
        WeekIdentifier weekId = new WeekIdentifier(weekOfYear, year, billingDate);
        
        WeeklySchedule weeklySchedule = controller.getPrevWeeklySchedule(instructorId, weekId);
        return Response.ok().entity(gson.toJson(weeklySchedule)).build();
    }
}
