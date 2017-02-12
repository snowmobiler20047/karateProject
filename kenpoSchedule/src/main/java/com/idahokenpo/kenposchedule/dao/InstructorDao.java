package com.idahokenpo.kenposchedule.dao;

import com.google.common.collect.Lists;
import com.idahokenpo.kenposchedule.data.Instructor;
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
public class InstructorDao extends AbstractDao<Instructor>
{
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.INSTRUCTORS.getCollectionName());

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

    @Override
    protected MongoCollection getCollection()
    {
        return collection;
    }

    @Override
    public List<Instructor> getAll()
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

    @Override
    public List<Instructor> get(Set<String> ids)
    {  
        List<Instructor> instructors = new ArrayList();
        FindIterable iterable = collection.find(Filters.in(CollectionNamesHelper.INSTRUCTORS.getKeyId(), ids));
        for (Object object : iterable)
        {
            Document doc = (Document) object;
            instructors.add(gson.fromJson(doc.toJson(), Instructor.class));
        }
        return instructors;
    }

    @Override
    public Instructor get(String id)
    {
        Document doc = (Document) collection.find(eq(CollectionNamesHelper.INSTRUCTORS.getKeyId(), id)).first();
        if (doc == null)
        {
            throw new IllegalArgumentException("Instructor ID: " + id + " doesn't exist");
        }
        return gson.fromJson(doc.toJson(), Instructor.class);
    }

    @Override
    public void update(Set<Instructor> values)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   @Override
    public void update(Instructor value)
    {
        String json = gson.toJson(value);

        collection.replaceOne(eq(CollectionNamesHelper.INSTRUCTORS.getKeyId(), value.getPersonId()), Document.parse(json));
    }

    @Override
    public void insert(Instructor instructor)
    {
        String json = gson.toJson(instructor);
        System.out.println(json);
        Instructor instructor2 = gson.fromJson(json, Instructor.class);
        System.out.println(instructor2);

        collection.insertOne(Document.parse(json));
    }  
}
