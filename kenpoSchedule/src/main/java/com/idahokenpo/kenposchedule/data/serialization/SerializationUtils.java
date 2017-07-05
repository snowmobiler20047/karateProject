package com.idahokenpo.kenposchedule.data.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.idahokenpo.kenposchedule.billing.Transaction;
import com.idahokenpo.kenposchedule.data.WeekIdentifier;

/**
 *
 * @author Korey
 */
public class SerializationUtils
{
    private static Gson gson;
    
    public static Gson getGson()
    {
        if(gson == null)
        {
            gson = new GsonBuilder()
                .registerTypeAdapter(WeekIdentifier.class, new WeekIdentifierSerializer())
                .registerTypeAdapter(WeekIdentifier.class, new WeekIdentifierDeserializer())
		.registerTypeAdapter(Transaction.class, new TransactionSerializer())
		.registerTypeAdapter(Transaction.class, new TransactionDeserializer())
                .enableComplexMapKeySerialization()
                .create();
        }
        
        return gson;
    }
}
