package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.billing.Account;
import com.idahokenpo.kenposchedule.billing.Payment;
import com.idahokenpo.kenposchedule.dao.AccountDao;
import com.idahokenpo.kenposchedule.dao.InstructorDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
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
@Path("account")
public class AccountResource
{
    private final AccountDao accountDao = new AccountDao();
    private final InstructorDao instructorDao = new InstructorDao();
    private final Gson gson = SerializationUtils.getGson();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAccount")
    public Response getAccount(@QueryParam("accountId") String accountId)
    {
        Account account = accountDao.get(accountId);
        
        return Response.ok(gson.toJson(account)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAccounts")
    public Response getAccounts()
    {
        List<Account> accounts = accountDao.getAll();
        
        return Response.ok(gson.toJson(accounts)).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAccount(@FormParam("name") String name)
    {
        Account account = new Account();
        account.setActive(true);
        
        accountDao.insert(account);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("applyPayment")
    public Response applyPayment(@FormParam("accountId") String accountId,
            @FormParam("amount") Double amount,
            @FormParam("date") String dateString,
            @FormParam("time") String timeString)
    {
        Account account = accountDao.get(accountId);
        
        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.parse(timeString);
        
        Payment payment = new Payment(amount, date, time);
        
        account.applyPayment(payment);
        
        accountDao.update(account);
        
        return Response.ok("Payment was successful!").build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("applyLessonCost")
    public Response applyLessonCost(@FormParam("accountId") String accountId,
            @FormParam("lessonId") String lessonId,
            @FormParam("instructorId") String instructorId,
            @FormParam("date") String dateString)
    {
        Account account = accountDao.get(accountId);
        Instructor instructor = instructorDao.get(instructorId);
        //TODO get the weekId and use that to load the correct weeklySchedule
        //        instructor.getSchedule().getWeeklyScheduleIdMap()
        //TODO get the lesson from the weeklySchedule
        
        LocalDate date = LocalDate.parse(dateString);
        
        
//        account.applyLessonCost(date, lesson, instructor);
        
        return Response.ok("Applying lesson cost was successful!").build();
    } 
}