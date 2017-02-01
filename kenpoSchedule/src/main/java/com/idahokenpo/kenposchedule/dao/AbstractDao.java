package com.idahokenpo.kenposchedule.dao;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import com.idahokenpo.kenposchedule.utils.DatabaseUtils;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Korey
 * @param <T>
 */
public abstract class AbstractDao<T>
{
    protected final MongoDatabase database = DatabaseUtils.getDatabase();
    protected final Gson gson = SerializationUtils.getGson();
    
    protected abstract MongoCollection getCollection();
    public abstract List<T> getAll();
    public abstract List<T> get(Set<String> ids);
    public abstract T get(String id);
    public abstract void update(Set<T> values);
    public abstract void update(T value);
    public abstract void insert(T value);
    
    /**
     * Probably shouldn't leave this here forever It will drop the
     * collection in mongodb :(
     */
    public void drop()
    {
        getCollection().drop();
    }
//    public List<T> getAll()
//    {
//        List<T> list = Lists.newArrayList();
//        for (Object object : getCollection().find())
//        {
//            Document doc = (Document) object;
//            T student = gson.fromJson(doc.toJson(), T.class);
//            list.add(student);
//        }
//
//        return list;
//    }
    
}
