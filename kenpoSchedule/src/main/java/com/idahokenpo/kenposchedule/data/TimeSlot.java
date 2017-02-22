package com.idahokenpo.kenposchedule.data;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
@Data
public class TimeSlot implements Comparable<TimeSlot>
{
    private String id;
    private final KenpoTime startTime;
    private final KenpoTime endTime;
    private Lesson lesson;

    public TimeSlot(KenpoTime startTime, KenpoTime endTime)
    {
        this.id = new ObjectId().toHexString();
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    public TimeSlot(TimeSlot ts)
    {
        this.id = ts.getId();
        this.startTime = ts.getStartTime();
        this.endTime = ts.getEndTime();
        this.lesson = new Lesson(lesson);
    }
    
    @Override
    public int compareTo(TimeSlot o)
    {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
