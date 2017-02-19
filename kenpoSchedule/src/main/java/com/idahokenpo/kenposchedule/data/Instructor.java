package com.idahokenpo.kenposchedule.data;

import lombok.Data;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
@Data
public class Instructor extends Person
{
    private LessonCost lessonCost;
    private Schedule schedule;
    private WeeklySchedule permenantSchedule;

    public Instructor()
    {
        this.personId = new ObjectId().toHexString();
    }
    
    
}
