package com.idahokenpo.kenposchedule.utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Korey
 */
public class DatabaseUtils
{
    private static final MongoClient client = new MongoClient("localhost", 27017);
    private static final String DATABASE_NAME = "ScheduleDatabase";
    
    public static MongoClient getClient()
    {
        return client;
    }
    
    public static MongoDatabase getDatabase()
    {
        return client.getDatabase(DATABASE_NAME);
    }
}
