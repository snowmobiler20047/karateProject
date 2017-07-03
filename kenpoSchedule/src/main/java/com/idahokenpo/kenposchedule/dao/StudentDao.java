package com.idahokenpo.kenposchedule.dao;

import com.idahokenpo.kenposchedule.data.Student;
import com.mongodb.client.MongoCollection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Korey
 */
public class StudentDao extends AbstractDao<Student>
{
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.STUDENTS.getCollectionName());

    @Override
    protected MongoCollection getCollection()
    {
        return collection;
    }

    @Override
    public List<Student> getAll()
    {
        return super.getAll(Student.class);
    }

    @Override
    public List<Student> get(Set<String> ids)
    {
        return super.get(CollectionNamesHelper.STUDENTS.getKeyId(), ids, Student.class);
    }

    @Override
    public Student get(String id)
    {
        return super.get(CollectionNamesHelper.STUDENTS.getKeyId(), id, Student.class);
    }

    @Override
    public void update(Set<Student> values)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Student value)
    {
        super.update(CollectionNamesHelper.STUDENTS.getKeyId(), value.getPersonId(), value);
    }
}
