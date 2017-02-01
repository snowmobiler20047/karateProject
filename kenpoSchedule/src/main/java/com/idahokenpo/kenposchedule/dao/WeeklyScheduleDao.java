package com.idahokenpo.kenposchedule.dao;

import com.idahokenpo.kenposchedule.data.WeeklySchedule;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bson.Document;

/**
 *
 * @author Korey
 */
public class WeeklyScheduleDao extends AbstractDao<WeeklySchedule>
{
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.WEEKLY_SCHEDULE.getCollectionName());

    @Override
    protected MongoCollection getCollection()
    {
        return collection;
    }

    @Override
    public List<WeeklySchedule> getAll()
    {
        List<WeeklySchedule> weeklySchedules = new ArrayList();
        for (Object object : collection.find())
        {
            Document doc = (Document) object;
            WeeklySchedule weeklySchedule = gson.fromJson(doc.toJson(), WeeklySchedule.class);
            weeklySchedules.add(weeklySchedule);
        }

        return weeklySchedules;
    }

    @Override
    public List<WeeklySchedule> get(Set<String> ids)
    {   
        List<WeeklySchedule> weeklySchedules = new ArrayList();
        FindIterable iterable = collection.find(Filters.in(CollectionNamesHelper.WEEKLY_SCHEDULE.getKeyId(), ids));
        for (Object object : iterable)
        {
            Document doc = (Document) object;
            weeklySchedules.add(gson.fromJson(doc.toJson(), WeeklySchedule.class));
        }
        return weeklySchedules;
    }

    @Override
    public WeeklySchedule get(String id)
    {
        Document doc = (Document) collection.find(eq(CollectionNamesHelper.WEEKLY_SCHEDULE.getKeyId(), id)).first();
        if (doc == null)
        {
            throw new IllegalArgumentException("WeeklySchedule ID: " + id + " doesn't exist");
        }
        return gson.fromJson(doc.toJson(), WeeklySchedule.class);
    }

    @Override
    public void update(Set<WeeklySchedule> values)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(WeeklySchedule value)
    {
         String json = gson.toJson(value);

        collection.replaceOne(eq(CollectionNamesHelper.WEEKLY_SCHEDULE.getKeyId(), value.getWeeklyScheduleId()), Document.parse(json));
    }

    @Override
    public void insert(WeeklySchedule value)
    {
        String json = gson.toJson(value);
        collection.insertOne(Document.parse(json));
    }
    
    
}
