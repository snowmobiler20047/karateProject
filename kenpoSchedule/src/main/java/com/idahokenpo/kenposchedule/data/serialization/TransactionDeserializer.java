/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.data.serialization;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.idahokenpo.kenposchedule.billing.InitialTransaction;
import com.idahokenpo.kenposchedule.billing.LessonTransaction;
import com.idahokenpo.kenposchedule.billing.Payment;
import com.idahokenpo.kenposchedule.billing.Transaction;
import com.idahokenpo.kenposchedule.billing.TransactionType;
import java.lang.reflect.Type;

/**
 *
 * @author Korey
 */
public class TransactionDeserializer implements JsonDeserializer<Transaction>
{
    @Override
    public Transaction deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException
    {
	Gson gson = new Gson();
	JsonObject obj = json.getAsJsonObject();
	
	TransactionType tranType = TransactionType.valueOf(obj.getAsJsonPrimitive("type").getAsString());
	
	switch(tranType)
	{
	    case PAYMENT:	
		return gson.fromJson(json, Payment.class);
	    case LESSON:
		return gson.fromJson(json, LessonTransaction.class);
	    case INITIAL:
		return gson.fromJson(json, InitialTransaction.class);		    
	}
	
	return new InitialTransaction();
    }
       
}
