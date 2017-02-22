package com.idahokenpo.kenposchedule.data;

import java.util.NavigableMap;
import java.util.TreeMap;
import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class Schedule
{
    transient private NavigableMap<WeekIdentifier, WeeklySchedule> weeklyScheduleMap;
    private NavigableMap<WeekIdentifier, String> weeklyScheduleIdMap;
    
    public Schedule()
    {
        weeklyScheduleMap = new TreeMap();
        weeklyScheduleIdMap = new TreeMap();
        WeekIdentifier weekId = new WeekIdentifier();
        WeeklySchedule weeklySchedule = new WeeklySchedule();
        weeklyScheduleMap.put(weekId, weeklySchedule);
        weeklyScheduleIdMap.put(weekId, weeklySchedule.getWeeklyScheduleId());                
    }

    public void addNextWeek(WeeklySchedule weeklySchedule)
    {
        WeekIdentifier weekId = weeklyScheduleIdMap.lastKey();
        WeekIdentifier nextWeekId = new WeekIdentifier(weekId.getBillingDate().plusWeeks(1));
        
        weeklyScheduleMap.put(nextWeekId, weeklySchedule);
        weeklyScheduleIdMap.put(nextWeekId, weeklySchedule.getWeeklyScheduleId());
    }

    public void addPrevWeek(WeeklySchedule weeklySchedule)
    {
        WeekIdentifier weekId = weeklyScheduleIdMap.firstKey();
        WeekIdentifier nextWeekId = new WeekIdentifier(weekId.getBillingDate().minusWeeks(1));
        
        weeklyScheduleMap.put(nextWeekId, weeklySchedule);
        weeklyScheduleIdMap.put(nextWeekId, weeklySchedule.getWeeklyScheduleId());
    }
    
}
