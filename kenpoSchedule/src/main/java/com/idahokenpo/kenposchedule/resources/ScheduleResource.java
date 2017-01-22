package com.idahokenpo.kenposchedule.resources;

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
    @GET
    @Produces("application/json")
    public Response getStudents()
    {
        return Response.status(Response.Status.OK).entity("Student Name").build();
    }
}
