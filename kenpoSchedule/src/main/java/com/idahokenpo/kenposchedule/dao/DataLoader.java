package com.idahokenpo.kenposchedule.dao;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.Student;
import com.idahokenpo.kenposchedule.utils.DatabaseUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Korey
 */
public class DataLoader
{   
    public void createDatabase()
    {
        MongoDatabase database = DatabaseUtils.getDatabase();
//        if (database.getCollection("students") != null)
//            database.createCollection("students");
    }
    
    
    
}
