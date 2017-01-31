/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.data.serialization;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.idahokenpo.kenposchedule.data.WeekIdentifier;
import java.lang.reflect.Type;

/**
 *
 * @author Korey
 */
public class WeekIdentifierDeserializer implements JsonDeserializer<WeekIdentifier>
{
    @Override
    public WeekIdentifier deserialize(JsonElement json, Type type, JsonDeserializationContext jdc) throws JsonParseException
    {
        JsonObject obj = json.getAsJsonObject();
        int weekOfYear = obj.getAsJsonPrimitive("weekOfYear").getAsInt();
        int year = obj.getAsJsonPrimitive("year").getAsInt();
        
        return new WeekIdentifier(weekOfYear, year);
    }
}
