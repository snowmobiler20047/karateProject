package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.Controller;
import com.idahokenpo.kenposchedule.dao.LessonDao;
import com.idahokenpo.kenposchedule.dao.WeeklyScheduleDao;
import com.idahokenpo.kenposchedule.data.KenpoTime;
import com.idahokenpo.kenposchedule.data.LessonType;
import com.idahokenpo.kenposchedule.data.TimeSlot;
import com.idahokenpo.kenposchedule.data.WeekIdentifier;
import com.idahokenpo.kenposchedule.data.WeeklySchedule;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import io.swagger.annotations.Api;
import java.time.DayOfWeek;
import java.time.LocalDate;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
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
@Singleton
@Path("schedule")
@Api
public class ScheduleResource 
{
    private static final WeeklyScheduleDao weeklyScheduleDao = new WeeklyScheduleDao();
    private static final LessonDao LESSON_DAO = new LessonDao();
    Gson gson = SerializationUtils.getGson();
    private final Controller controller = new Controller();
    
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
        LocalDate billingDate = LocalDate.parse(billingDateString);
        WeekIdentifier weekId = new WeekIdentifier(weekOfYear, year, billingDate);
        
        WeeklySchedule weeklySchedule = controller.getPrevWeeklySchedule(instructorId, weekId);
        return Response.ok().entity(gson.toJson(weeklySchedule)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("addTimeslot")
    public Response addTimeslot(@FormParam("weeklyScheduleId") String weeklyScheduleId,
            @FormParam("day") String dayString,
            @FormParam("startTime") String startTimeString,
            @FormParam("endTime") String endTimeString)
    {
        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);
        
        DayOfWeek day = DayOfWeek.valueOf(dayString);
        KenpoTime startTime = KenpoTime.fromString(startTimeString);
        KenpoTime endTime = KenpoTime.fromString(endTimeString);
        TimeSlot timeSlot = new TimeSlot(startTime, endTime);
        
        weeklySchedule.addTimeSlot(day, timeSlot);
        weeklyScheduleDao.update(weeklySchedule);
        
        return Response.ok("TimeSlot added!").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("addLesson")
    public Response addLesson(@FormParam("weeklyScheduleId") String weeklyScheduleId,
            @FormParam("day") DayOfWeek day,
            @FormParam("timeSlotId") String timeSlotId,
            @FormParam("lessonType") LessonType lessonType,
            @FormParam("accountId") String accountId)
    {
        controller.addLesson(weeklyScheduleId, day, timeSlotId, lessonType, accountId);
        
        return Response.ok("Lesson created!").build();
    }
    
    
//    More of a billing sort of a thing
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Path("addLessonLink")
//    public Response addLessonLink(@FormParam("weeklyScheduleId") String weeklyScheduleId,
//            @FormParam("primaryLessonId") String primaryLessonId,
//            @FormParam("lessonId") List<String> lessonIds)
//    {
//        
//        
//        return Response.ok("Lesson created!").build();
//    }
}
