package com.idahokenpo.kenposchedule.dao;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import com.idahokenpo.kenposchedule.utils.DatabaseUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Korey
 */
public class InstructorDao
{
    private final MongoDatabase database = DatabaseUtils.getDatabase();
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.INSTRUCTORS.getCollectionName());
    private final Gson gson = SerializationUtils.getGson();
    
    public List<Instructor> getInstructors()
    {
        List<Instructor> instructors = Lists.newArrayList();
        for (Object object : collection.find())
        {
            Document doc = (Document) object;
            System.out.println(doc);
            Instructor instructor = gson.fromJson(doc.toJson(), Instructor.class);
            instructors.add(instructor);
        }

        return instructors;
    }
    
    public void insertInstructor(Instructor instructor)
    {
        String json = gson.toJson(instructor);
        System.out.println(json);
        Instructor instructor2 = gson.fromJson(json, Instructor.class);
        System.out.println(instructor2);
        
        collection.insertOne(Document.parse(json));
    }
    public void clear()
    {
        collection.drop();
    }
}
