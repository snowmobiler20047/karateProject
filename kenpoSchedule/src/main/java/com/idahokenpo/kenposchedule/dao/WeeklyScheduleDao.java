/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.dao;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import com.idahokenpo.kenposchedule.utils.DatabaseUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Korey
 */
public class WeeklyScheduleDao extends AbstractDao
{
    private final MongoDatabase database = DatabaseUtils.getDatabase();
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.WEEKLY_SCHEDULE.getCollectionName());
    private final Gson gson = SerializationUtils.getGson();

    @Override
    protected MongoCollection getCollection()
    {
        return collection;
    }
    
    
}
