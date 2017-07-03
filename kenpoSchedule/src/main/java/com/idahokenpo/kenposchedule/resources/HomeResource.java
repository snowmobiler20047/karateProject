/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.resources;

import io.swagger.annotations.Api;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author Korey
 */
@Singleton
@Path("/")
@Api
public class HomeResource
{
    @GET
    public Response getHi()
    {
        return Response.ok("Hi").build();
    }
}
