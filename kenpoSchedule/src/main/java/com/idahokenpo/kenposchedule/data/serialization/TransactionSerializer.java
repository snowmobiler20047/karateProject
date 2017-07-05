package com.idahokenpo.kenposchedule.data.serialization;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.idahokenpo.kenposchedule.billing.InitialTransaction;
import com.idahokenpo.kenposchedule.billing.LessonTransaction;
import com.idahokenpo.kenposchedule.billing.Payment;
import com.idahokenpo.kenposchedule.billing.Transaction;
import java.lang.reflect.Type;

/**
 *
 * @author Korey
 */
public class TransactionSerializer implements JsonSerializer<Transaction>
{
    @Override
    public JsonElement serialize(Transaction t, Type type, JsonSerializationContext jsc)
    {
	Gson gson = new Gson();
	
	switch(t.getTransactionType())
	{
	    case PAYMENT:
	    {
		Payment p = (Payment) t;
		return gson.toJsonTree(p);
	    }
	    case LESSON:
	    {
		LessonTransaction l = (LessonTransaction) t;
		return gson.toJsonTree(l);
	    }
	    case INITIAL:
	    {
		InitialTransaction i = (InitialTransaction) t;
		return gson.toJsonTree(i);
	    }
		    
	}
		
	return gson.toJsonTree(t);	
    }
     
}
