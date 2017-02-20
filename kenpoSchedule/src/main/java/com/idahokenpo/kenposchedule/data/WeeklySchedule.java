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
    private Map<String, Lesson> lessonMap;
    
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
    
    public void addTimeSlot(DayOfWeek dayOfWeek, TimeSlot timeslot)
    {
        NavigableSet<TimeSlot> timeslotSet = dayToTimeslotsMap.get(dayOfWeek);
        if (timeslotSet.contains(timeslot))
            throw new IllegalArgumentException("Timeslot already exists");
        timeslotSet.add(timeslot);
    }
    
    public void removeTimeSlot(DayOfWeek dayOfWeek, TimeSlot timeslot)
    {
        NavigableSet<TimeSlot> timeslotSet = dayToTimeslotsMap.get(dayOfWeek);
        timeslotSet.remove(timeslot);
    }
    
    public void addLesson(DayOfWeek dayOfWeek, TimeSlot timeslot, Lesson lesson)
    {
//        NavigableSet<TimeSlot> timeslots = dayToTimeslotsMap.get(dayOfWeek);
//        
//        for (TimeSlot ts : timeslots)
//        {
//            if (ts.equals(timeslot))
//        }
//        
    }

    public Lesson findLesson(String lessonId)
    {
        return lessonMap.get(lessonId);
    }
    
}
