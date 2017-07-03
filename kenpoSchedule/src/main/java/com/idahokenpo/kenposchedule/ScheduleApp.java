package com.idahokenpo.kenposchedule;

import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author Korey
 */
public class ScheduleApp extends ResourceConfig
{

    public ScheduleApp()
    {
        packages("com.idahokenpo.kenposchedule.resources");
        
//        register(GsonProvider.class);
    }
}
