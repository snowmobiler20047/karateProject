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

    public void addNextWeek(WeeklySchedule schedule)
    {
        
    }
    
}
