package com.idahokenpo.kenposchedule.dao;

import com.idahokenpo.kenposchedule.data.Lesson;
import com.mongodb.client.MongoCollection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Korey
 */
public class LessonDao extends AbstractDao<Lesson>
{
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.LESSON.getCollectionName());
    
    @Override
    protected MongoCollection getCollection()
    {
        return this.collection;
    }

    @Override
    public List<Lesson> getAll()
    {
        return super.getAll(Lesson.class);
    }

    @Override
    public List<Lesson> get(Set<String> ids)
    {
        return super.get(CollectionNamesHelper.LESSON.getKeyId(), ids, Lesson.class);
    }

    @Override
    public Lesson get(String id)
    {
        return super.get(CollectionNamesHelper.LESSON.getKeyId(), id, Lesson.class);    
    }

    @Override
    public void update(Set<Lesson> values)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Lesson lesson)
    {
        super.update(CollectionNamesHelper.LESSON.getKeyId(), lesson.getLessonId(), lesson);
    }
    
}
