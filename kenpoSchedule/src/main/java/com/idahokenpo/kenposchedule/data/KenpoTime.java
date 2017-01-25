package com.idahokenpo.kenposchedule.data;

/**
 *
 * @author Korey
 */
public class KenpoTime implements Comparable<KenpoTime>
{
    private final KenpoHour hour;
    private final KenpoMinute minute;
    private final boolean isMorning;

    public KenpoTime(KenpoHour hour, KenpoMinute minute, boolean isMorning)
    {
        this.hour = hour;
        this.minute = minute;
        this.isMorning = isMorning;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(hour.getDisplayValue()).append(":");
        sb.append(minute.getDisplayValue());
        sb.append(isMorning ? "AM" : "PM");
        
        return sb.toString();
    }

    @Override
    public int compareTo(KenpoTime o)
    {
        //same time
        if(this.hour.equals(o.getHour()) && this.minute.equals(o.getMinute()) && this.isMorning == o.isMorning())
            return 0;
        //check to see if they are both AM
        if(this.isMorning != o.isMorning())
        {
            if(this.isMorning == true)
                return -1;
            else
                return 1;
        }
        
        if(this.hour.ordinal() < o.getHour().ordinal())
            return -1;
        else if(this.hour.ordinal() > o.getHour().ordinal())
            return 1;
        else        //same hour
        {
            if(this.minute.ordinal() < o.getMinute().ordinal())
                return -1;
            else 
                return 1;
        }        
    }

    public KenpoHour getHour()
    {
        return hour;
    }

    public KenpoMinute getMinute()
    {
        return minute;
    }

    public boolean isMorning()
    {
        return isMorning;
    }
}
