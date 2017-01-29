package com.idahokenpo.kenposchedule.dao;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.Student;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

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
    
    public List<Student> getStudents()
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        Gson gson = new Gson();
        List<Student> students = Lists.newArrayList();
        for (Object object : collection.find())
        {
            Document doc = (Document) object;
            System.out.println(doc);
            Student student = gson.fromJson(doc.toJson(), Student.class);
            students.add(student);
        }
        
        return students;
    }
    
    public Student getStudent(String studentId)
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        
        Gson gson = new Gson();
        Document doc = (Document) collection.find(eq("personId", studentId)).first();
        if (doc == null)
            throw new IllegalArgumentException("Student ID: " + studentId + " doesn't exist");
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
        System.out.println(json);
        
        collection.replaceOne(eq("personId", student.getPersonId()), Document.parse(json));
    }
    
    /**
     * Probably shouldn't leave this here forever 
     * It will drop the students collection in mongodb :(
     */
    public void deleteStudents()
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        
        collection.drop();
    }
    public void deleteStudent(Student student)
    {
        MongoDatabase database = client.getDatabase("ScheduleDatabase");
        MongoCollection collection = database.getCollection("students");
        
        Gson gson = new Gson();
        String json = gson.toJson(student);
        
        collection.deleteOne(eq("personId", student.getPersonId()));
    }
    
}
