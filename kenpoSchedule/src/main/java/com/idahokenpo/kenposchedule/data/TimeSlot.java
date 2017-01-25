package com.idahokenpo.kenposchedule.data;

import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class TimeSlot implements Comparable<TimeSlot>
{
    private final KenpoTime startTime;
    private final KenpoTime endTime;
    private Lesson lesson;

    public TimeSlot(KenpoTime startTime, KenpoTime endTime)
    {
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    @Override
    public int compareTo(TimeSlot o)
    {
        return this.getStartTime().compareTo(o.getStartTime());
    }
}
