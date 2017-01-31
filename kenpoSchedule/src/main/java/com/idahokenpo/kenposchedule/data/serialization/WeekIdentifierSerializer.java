/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.data.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.idahokenpo.kenposchedule.data.WeekIdentifier;
import java.lang.reflect.Type;

/**
 *
 * @author Korey
 */
public class WeekIdentifierSerializer implements JsonSerializer<WeekIdentifier>
{

    @Override
    public JsonElement serialize(WeekIdentifier weekId, Type type, JsonSerializationContext jsc)
    {   
        JsonObject obj = new JsonObject();
        obj.addProperty("weekOfYear", weekId.getWeekOfYear());
        obj.addProperty("year", weekId.getYear());
        return obj;
    }
    
}
