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
public class StudentDao
{
    private final MongoDatabase database = DatabaseUtils.getDatabase();
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.STUDENTS.getCollectionName());
    private final Gson gson = new Gson();

    public List<Student> getStudents()
    {
        List<Student> students = Lists.newArrayList();
        for (Object object : collection.find())
        {
            Document doc = (Document) object;
            Student student = gson.fromJson(doc.toJson(), Student.class);
            students.add(student);
        }

        return students;
    }

    public Student getStudent(String studentId)
    {
        Document doc = (Document) collection.find(eq(CollectionNamesHelper.STUDENTS.getKeyId(), studentId)).first();
        if (doc == null)
        {
            throw new IllegalArgumentException("Student ID: " + studentId + " doesn't exist");
        }
        return gson.fromJson(doc.toJson(), Student.class);
    }

    public void insertStudent(Student student)
    {
        String json = gson.toJson(student);
        collection.insertOne(Document.parse(json));
    }

    public void updateStudent(Student student)
    {
        String json = gson.toJson(student);
//        System.out.println(json);

        collection.replaceOne(eq(CollectionNamesHelper.STUDENTS.getKeyId(), student.getPersonId()), Document.parse(json));
    }

    /**
     * Probably shouldn't leave this here forever It will drop the students
     * collection in mongodb :(
     */
    public void deleteStudents()
    {
        collection.drop();
    }

    public void deleteStudent(Student student)
    {
        collection.deleteOne(eq(CollectionNamesHelper.STUDENTS.getKeyId(), student.getPersonId()));
    }
}
