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
public class WeeklySchedule
{
    private final Map<DayOfWeek, NavigableSet<TimeSlot>> dayToTimeslotsMap;
    
    public WeeklySchedule()
    {
        dayToTimeslotsMap = Maps.newTreeMap();
        for (DayOfWeek day : DayOfWeek.values())
        {
            NavigableSet<TimeSlot> timeSlotSet = Sets.newTreeSet();
            dayToTimeslotsMap.put(day, timeSlotSet);
        }
    }
    
}
