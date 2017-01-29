package com.idahokenpo.kenposchedule.dao;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.billing.Account;
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
public class AccountDao
{
    private final MongoDatabase database = DatabaseUtils.getDatabase();
    private final MongoCollection collection = database.getCollection(CollectionNamesHelper.ACCOUNTS.getCollectionName());
    private final Gson gson = new Gson();
    
    public List<Account> getAccounts()
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
    
    public Account getAccount(String accountId)
    {
        Document doc = (Document) collection.find(eq(CollectionNamesHelper.ACCOUNTS.getKeyId(), accountId)).first();
        if (doc == null)
        {
            throw new IllegalArgumentException("Account ID: " + accountId + " doesn't exist");
        }
        return gson.fromJson(doc.toJson(), Account.class);
    }
    
    
}
