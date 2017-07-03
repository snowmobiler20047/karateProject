package com.idahokenpo.kenposchedule.dao;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import com.idahokenpo.kenposchedule.utils.DatabaseUtils;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.bson.Document;

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
    
    /**
     * Probably shouldn't leave this here forever It will drop the
     * collection in mongodb :(
     */
    public void drop()
    {
        getCollection().drop();
    }
    
    protected List<T> getAll(Class<T> c)
    {
        List<T> list = new ArrayList();
        for (Object object : getCollection().find())
        {
            Document doc = (Document) object;
            list.add(gson.fromJson(doc.toJson(), c));
        }

        return list;
    }
    
    protected List<T> get(String keyId, Set<String> ids, Class<T> clazz)
    {
        List<T> values = new ArrayList();
        FindIterable iterable = getCollection().find(Filters.in(keyId, ids));
        for (Object object : iterable)
        {
            Document doc = (Document) object;
            values.add(gson.fromJson(doc.toJson(), clazz));
        }
        return values;
    }
    
    protected T get(String keyField, String id, Class<T> clazz)
    {
        Document doc = (Document) getCollection().find(eq(keyField, id)).first();
        if (doc == null)
        {
            throw new IllegalArgumentException(keyField + ": " + id + " doesn't exist");
        }
        return gson.fromJson(doc.toJson(), clazz);
    }
    
    protected void update(String keyId, String id, T value)
    {
        String json = gson.toJson(value);

        getCollection().replaceOne(eq(keyId, id), Document.parse(json));
    }
    
    public void insert(T value)
    {
        String json = gson.toJson(value);
        getCollection().insertOne(Document.parse(json));
    }
    
}
