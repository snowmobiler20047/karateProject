package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Maps;
import java.util.NavigableMap;
import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class Schedule
{
    private NavigableMap<WeekIdentifier, WeeklySchedule> weeklyScheduleMap;
    
    public Schedule()
    {
        weeklyScheduleMap = Maps.newTreeMap();
        
        weeklyScheduleMap.put(new WeekIdentifier(), new WeeklySchedule());
        
    }

    public void addNextWeek(WeeklySchedule schedule)
    {
        
    }
    
}
