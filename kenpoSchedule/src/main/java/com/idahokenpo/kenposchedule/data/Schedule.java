/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.NavigableSet;
import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class Schedule
{
    private final Map<DayOfWeek, NavigableSet<TimeSlot>> dayToTimeslotMap;

    public Schedule()
    {
        dayToTimeslotMap = Maps.newTreeMap();
        for (DayOfWeek day : DayOfWeek.values())
        {
            NavigableSet<TimeSlot> timeSlotSet = Sets.newTreeSet();
            dayToTimeslotMap.put(day, timeSlotSet);
        }
    }
    
    
}
