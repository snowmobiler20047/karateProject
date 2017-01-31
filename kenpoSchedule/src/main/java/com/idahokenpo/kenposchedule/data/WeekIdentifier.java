package com.idahokenpo.kenposchedule.data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class WeekIdentifier implements Comparable<WeekIdentifier>
{
    private int weekOfYear;
    private int year;

    public WeekIdentifier(int weekOfYear, int year)
    {
        this.weekOfYear = weekOfYear;
        this.year = year;
    }
    
    /**
     * create a new WeekIdentifier with defaulting to this week
     */
    public WeekIdentifier()
    {
        Calendar calendar = GregorianCalendar.getInstance();
        this.weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        this.year = calendar.get(Calendar.YEAR);
    }
    
    public WeekIdentifier(LocalDate referenceDate)
    {
        ZonedDateTime referenceDateTime = ZonedDateTime.of(referenceDate, LocalTime.NOON, ZoneId.systemDefault());
        GregorianCalendar calendar = GregorianCalendar.from(referenceDateTime);
        this.weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        this.year = calendar.get(Calendar.YEAR);
    }

    @Override
    public int compareTo(WeekIdentifier o)
    {
       if(this.weekOfYear == o.getWeekOfYear() && this.year == o.getYear())
           return 0;
       
       if (this.getYear() < o.getYear())
           return -1;
       else if (this.getYear() > o.getYear())
           return 1;
       else
           return this.getWeekOfYear() < o.getWeekOfYear() ? -1 : 1;
    }
}
