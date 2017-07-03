package com.idahokenpo.kenposchedule.data;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
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
    private LocalDate billingDate;
    
    /**
     * create a new WeekIdentifier with defaulting to this week
     */
    public WeekIdentifier()
    {
        this(GregorianCalendar.getInstance().get(Calendar.WEEK_OF_YEAR),
                GregorianCalendar.getInstance().get(Calendar.YEAR), 
                LocalDate.now().with(WeekFields.of(Locale.US).dayOfWeek(), 7));
    }

    public WeekIdentifier(int weekOfYear, int year, LocalDate billingDate)
    {
        this.weekOfYear = weekOfYear;
        this.year = year;
        this.billingDate = billingDate;
    }
    
    public WeekIdentifier(LocalDate referenceDate)
    {
        ZonedDateTime referenceDateTime = ZonedDateTime.of(referenceDate, LocalTime.NOON, ZoneId.systemDefault());
        GregorianCalendar calendar = GregorianCalendar.from(referenceDateTime);
        this.weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        this.year = calendar.get(Calendar.YEAR);
        this.billingDate = referenceDate;
    }

    public WeekIdentifier fromString(String str)
    {
        Gson gson = SerializationUtils.getGson();
        
        return gson.fromJson(str, WeekIdentifier.class);
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
