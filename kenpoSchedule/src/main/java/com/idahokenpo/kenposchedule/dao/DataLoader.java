package com.idahokenpo.kenposchedule.dao;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.Student;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
public class DataLoader
{
    private static final MongoClient client = new MongoClient("localhost", 27017);
    
    public void createDatabase()
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
//        if (database.getCollection("students") != null)
//            database.createCollection("students");
    }
    
    public MongoCollection<Document> getStudents()
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        
        return collection;
    }
    
    public Student getStudent(String studentId)
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        
        Gson gson = new Gson();
        Document doc = (Document) collection.find(eq("_id", new ObjectId(studentId))).first();
        System.out.println(doc.toJson());
        return gson.fromJson(doc.toJson(), Student.class);
    }
    
    public void insertStudent(Student student)
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        Gson gson = new Gson();
        String json = gson.toJson(student);
        collection.insertOne(Document.parse(json));
    }
    
    public void updateStudent(Student student)
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        
        Gson gson = new Gson();
        String json = gson.toJson(student);
        
        collection.updateOne(eq("_id", new ObjectId("58881f094b6fb33573e2f683")), Document.parse(json));
    }
    
}
