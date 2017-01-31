package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
@Data
public class WeeklySchedule
{
    private String weeklyScheduleId;
    private WeekIdentifier weekIdentifier;
    private Map<DayOfWeek, NavigableSet<TimeSlot>> dayToTimeslotsMap;
    
    public WeeklySchedule()
    {
        this.weeklyScheduleId = new ObjectId().toHexString();
        this.weekIdentifier = null;
        dayToTimeslotsMap = Maps.newTreeMap();
        for (DayOfWeek day : DayOfWeek.values())
        {
            NavigableSet<TimeSlot> timeSlotSet = Sets.newTreeSet();
            dayToTimeslotsMap.put(day, timeSlotSet);
        }
    }
    
    public WeeklySchedule(WeeklySchedule referenceSchedule)
    {
        this.weeklyScheduleId = new ObjectId().toHexString();
        this.dayToTimeslotsMap = new TreeMap<>();
        for (Map.Entry<DayOfWeek, NavigableSet<TimeSlot>> entry : referenceSchedule.getDayToTimeslotsMap().entrySet())
        {
            NavigableSet<TimeSlot> timeSlotSet = new TreeSet(entry.getValue());
            dayToTimeslotsMap.put(entry.getKey(), timeSlotSet);
        }
        
    }
    
}
