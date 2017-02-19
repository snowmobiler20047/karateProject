package com.idahokenpo.kenposchedule.dao;

import com.google.common.collect.Lists;
import com.idahokenpo.kenposchedule.billing.Account;
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
public class AccountDao extends AbstractDao<Account>
{
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.ACCOUNTS.getCollectionName());

    @Override
    protected MongoCollection getCollection()
    {
        return this.collection; 
    }

    @Override
    public List<Account> getAll()
    {
        List<Account> accounts = Lists.newArrayList();
        for (Object object : collection.find())
        {
            Document doc = (Document) object;
            Account account = gson.fromJson(doc.toJson(), Account.class);
            accounts.add(account);
        }

        return accounts;
    }

    @Override
    public List<Account> get(Set<String> ids)
    {
        List<Account> accounts = new ArrayList();
        FindIterable iterable = collection.find(Filters.in(CollectionNamesHelper.WEEKLY_SCHEDULE.getKeyId(), ids));
        for (Object object : iterable)
        {
            Document doc = (Document) object;
            accounts.add(gson.fromJson(doc.toJson(), Account.class));
        }
        return accounts;
    }

    @Override
    public Account get(String id)
    {
        Document doc = (Document) collection.find(eq(CollectionNamesHelper.ACCOUNTS.getKeyId(), id)).first();
        if (doc == null)
        {
            throw new IllegalArgumentException("Account ID: " + id + " doesn't exist");
        }
        return gson.fromJson(doc.toJson(), Account.class);}

    @Override
    public void update(Set<Account> values)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Account value)
    {
        String json = gson.toJson(value);

        collection.replaceOne(eq(CollectionNamesHelper.ACCOUNTS.getKeyId(), value.getAccountId()), Document.parse(json));
    }

    @Override
    public void insert(Account value)
    {
        String json = gson.toJson(value);
        collection.insertOne(Document.parse(json));
    }
    
    
}
