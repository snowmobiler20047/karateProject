package com.idahokenpo.kenposchedule.resources;

import com.idahokenpo.kenposchedule.billing.BillingUtils;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("billing")
public class BillingResource
{
    BillingUtils billing = new BillingUtils();
    
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
}
