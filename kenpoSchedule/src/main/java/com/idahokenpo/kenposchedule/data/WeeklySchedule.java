package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Maps;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
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
    private Map<DayOfWeek, Map<String, TimeSlot>> dayToTimeslotsMap;

    public WeeklySchedule()
    {
        this.weeklyScheduleId = new ObjectId().toHexString();
        this.weekIdentifier = null;
        dayToTimeslotsMap = Maps.newTreeMap();
        for (DayOfWeek day : DayOfWeek.values())
        {
            Map<String, TimeSlot> timeSlotMap = new HashMap();
            dayToTimeslotsMap.put(day, timeSlotMap);
        }
    }

    public WeeklySchedule(WeeklySchedule referenceSchedule)
    {
        this.weeklyScheduleId = new ObjectId().toHexString();
        this.dayToTimeslotsMap = new TreeMap<>();
        for (Map.Entry<DayOfWeek, Map<String, TimeSlot>> entry : referenceSchedule.getDayToTimeslotsMap().entrySet())
        {
            Map<String, TimeSlot> timeSlotMap = new HashMap();
            for (TimeSlot timeSlot : entry.getValue().values())
            {
                TimeSlot newTimeSlot = new TimeSlot(timeSlot);
                timeSlotMap.put(newTimeSlot.getId(), timeSlot);
            }

            dayToTimeslotsMap.put(entry.getKey(), timeSlotMap);
        }
    }

    public void addTimeSlot(DayOfWeek dayOfWeek, TimeSlot timeslot)
    {
        Map<String, TimeSlot> timeSlotMap = dayToTimeslotsMap.get(dayOfWeek);
        if (timeSlotMap.containsValue(timeslot))
        {
            throw new IllegalArgumentException("Timeslot already exists");
        }
        timeSlotMap.put(timeslot.getId(), timeslot);
    }

    public void removeTimeSlot(DayOfWeek dayOfWeek, TimeSlot timeslot)
    {
        Map<String, TimeSlot> timeSlotMap = dayToTimeslotsMap.get(dayOfWeek);
        timeSlotMap.remove(timeslot.getId());
    }

    public TimeSlot getTimeSlot(DayOfWeek dayOfWeek, String timeSlotId)
    {
        Map<String, TimeSlot> timeSlotMap = dayToTimeslotsMap.get(dayOfWeek);
        return timeSlotMap.get(timeSlotId);
    }

    public void addLesson(DayOfWeek dayOfWeek, String timeSlotId, Lesson lesson)
    {
        Map<String, TimeSlot> timeSlotMap = dayToTimeslotsMap.get(dayOfWeek);
        timeSlotMap.get(timeSlotId).setLesson(lesson);
    }

    public Lesson findLesson(String lessonId)
    {
        for (Map<String, TimeSlot> timeSlotMap : dayToTimeslotsMap.values())
        {
            for (TimeSlot timeSlot : timeSlotMap.values())
            {
                Lesson lesson = timeSlot.getLesson();
                if (lesson != null)
                {
                    if (lesson.getLessonId().equals(lessonId))
                    {
                        return lesson;
                    }
                }
            }
        }
        throw new IllegalArgumentException("LessonId: " + lessonId + " does not exist in weekly schedule.");
    }

    public void removeLesson(String lessonId)
    {
        for (Map<String, TimeSlot> timeSlotMap : dayToTimeslotsMap.values())
        {
            for (TimeSlot timeSlot : timeSlotMap.values())
            {
                Lesson lesson = timeSlot.getLesson();
                if (lesson != null)
                {
                    if (lesson.getLessonId().equals(lessonId))
                    {
                        timeSlot.setLesson(null);
                    }

                }
            }
        }
    }

}
