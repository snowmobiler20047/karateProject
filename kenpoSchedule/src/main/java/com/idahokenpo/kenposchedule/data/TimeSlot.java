package com.idahokenpo.kenposchedule.data;

import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class TimeSlot
{
    private KenpoTime startTime;
    private KenpoTime endTime;
    private Lesson lessons;
}
