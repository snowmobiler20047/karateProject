package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.billing.Account;
import com.idahokenpo.kenposchedule.billing.Payment;
import com.idahokenpo.kenposchedule.dao.AccountDao;
import com.idahokenpo.kenposchedule.dao.InstructorDao;
import com.idahokenpo.kenposchedule.dao.WeeklyScheduleDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import com.idahokenpo.kenposchedule.data.LessonLink;
import com.idahokenpo.kenposchedule.data.LessonLink.LessonLinkBuilder;
import com.idahokenpo.kenposchedule.data.WeekIdentifier;
import com.idahokenpo.kenposchedule.data.WeeklySchedule;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
        List<Account> accounts = accountDao.getAll().stream().filter(a -> a.isActive() == true).collect(Collectors.toList());

        return Response.ok(gson.toJson(accounts)).build();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getAllAccounts")
    public Response getAllAccounts()
    {
        List<Account> accounts = accountDao.getAll();

        return Response.ok(gson.toJson(accounts)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("create")
    public Response createAccount(@FormParam("accountName") String accountName)
    {
        Account account = new Account(accountName);

        accountDao.insert(account);
	
	return Response.ok("Successfully created account: " + accountName).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("create")
    public Response createAccountJson(String accountName)
    {
        Account account = new Account(accountName);

        accountDao.insert(account);
	
	return Response.ok("Successfully created account: " + accountName).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("edit")
    public Response editAccount(@FormParam("accountId") String accountId,
            @FormParam("accountName") String accountName,
            @FormParam("active") Boolean active)
    {
        Account account = accountDao.get(accountId);
        if (accountName != null && !accountName.equals(account.getName()))
        {
            account.setName(accountName);
        }
        if (active != null && active != account.isActive())
        {
            account.setActive(active);
        }

        accountDao.update(account);
	
	return Response.ok(gson.toJson(account)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("applyPayment")
    public Response applyPayment(@FormParam("accountId") String accountId,
            @FormParam("amount") Double amount,
            @FormParam("date") String dateString)
    {
        Account account = accountDao.get(accountId);

        LocalDate date = LocalDate.parse(dateString);
        LocalTime time = LocalTime.now();

        Payment payment = new Payment(amount, date, time);

        account.applyPayment(payment);

        accountDao.update(account);

        return Response.ok("Payment was successful!").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("editPayment")
    public Response editPayment(@FormParam("accountId") String accountId,
            @FormParam("paymentId") String paymentId,
            @FormParam("amount") Double amount,
            @FormParam("date") String dateString)
    {
        Account account = accountDao.get(accountId);
        
        Payment payment = new Payment(paymentId, amount, LocalDate.parse(dateString), LocalTime.now());
	account.editPayment(payment);
        
        accountDao.update(account);

        return Response.ok("Update of payment was successful!").build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("applyLessonCost")
    public Response applyLessonCost(@FormParam("accountId") String accountId,
            @FormParam("lessonId") String lessonId,
            @FormParam("instructorId") String instructorId,
            @FormParam("date") String dateString,
            @FormParam("weekOfYear") Integer weekOfYear,
            @FormParam("year") Integer year,
            @FormParam("billingDate") String billingDateString)
    {
        Account account = accountDao.get(accountId);
        Instructor instructor = instructorDao.get(instructorId);
        LocalDate billingDate = LocalDate.parse(billingDateString);
        WeekIdentifier weekId = new WeekIdentifier(weekOfYear, year, billingDate);
        String weeklyScheduleId = instructor.getSchedule().getWeeklyScheduleIdMap().get(weekId);

        WeeklyScheduleDao weeklyScheduleDao = new WeeklyScheduleDao();
        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);

        Lesson lesson = weeklySchedule.findLesson(lessonId);
        LocalDate date = LocalDate.parse(dateString);

        account.applyLessonCost(date, lesson, instructor);
        accountDao.update(account);

        return Response.ok("Applying lesson cost was successful!").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("createLessonLink")
    public Response createLessonLink(@FormParam("accountId") String accountId,
            @FormParam("primaryLessonId") String primaryLessonId,
            @FormParam("lessonId") Set<String> lessonIds)
    {
        Account account = accountDao.get(accountId);

        LessonLinkBuilder builder = new LessonLink.LessonLinkBuilder(primaryLessonId);
        builder.withAdditionalLessons(lessonIds);

        LessonLink lessonLink = builder.build();

        account.setLessonLink(lessonLink);
        accountDao.update(account);
        
        return Response.ok().build();
    }
}
