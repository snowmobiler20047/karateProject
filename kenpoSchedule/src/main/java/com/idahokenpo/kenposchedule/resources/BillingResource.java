package com.idahokenpo.kenposchedule.resources;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.billing.BillingUtils;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import java.time.LocalDate;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("billing")
public class BillingResource
{
    BillingUtils billing = new BillingUtils();
    Gson gson = SerializationUtils.getGson();
    
    @Path("runall")
    @GET
    public Response getBillingReport()
    {
	return Response.ok(billing.runReport()).build();
    }
    
    @Path("run")
    @GET
    public Response getBillingReport(@QueryParam("accountId") String accountId)
    {
	return Response.ok(billing.runReportForAccount(accountId)).build();
    }
    
    @Path("income")
    @GET
    
    public Response getIncome(@QueryParam("startDate") String startDateString, @QueryParam("endDate") String endDateString)
    {
	LocalDate startDate = LocalDate.parse(startDateString);
	LocalDate endDate = LocalDate.parse(endDateString);
	
	if (startDate.isAfter(endDate))
	    return Response.serverError().entity("Start date needs to be before endDate").build();
	
	return Response.ok(gson.toJson(billing.runIncomeReport(startDate, endDate))).build();
    }
}
