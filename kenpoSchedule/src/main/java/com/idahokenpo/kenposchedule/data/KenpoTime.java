package com.idahokenpo.kenposchedule.data;

/**
 *
 * @author Korey
 */
public class KenpoTime implements Comparable<KenpoTime>
{
    private final KenpoHour hour;
    private final KenpoMinute minute;
    private final boolean PM;

    public KenpoTime(KenpoHour hour, KenpoMinute minute, boolean PM)
    {
        this.hour = hour;
        this.minute = minute;
        this.PM = PM;
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(hour.getDisplayValue()).append(":");
        sb.append(minute.getDisplayValue());
        sb.append(PM ? "AM" : "PM");
        
        return sb.toString();
    }

    @Override
    public int compareTo(KenpoTime o)
    {
        //same time
        if(this.hour.equals(o.getHour()) && this.minute.equals(o.getMinute()) && this.PM == o.isPM())
            return 0;
        //check to see if they are both PM
        if(this.PM != o.isPM())
        {
            if(this.PM == true)
                return 1;
            else
                return -1;
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

    public boolean isPM()
    {
        return PM;
    }
    
    public static KenpoTime fromString(String startTimeString)
    {
        String[] split = startTimeString.split(": ");
        KenpoHour hour = KenpoHour.fromString(split[0]);
        KenpoMinute min = KenpoMinute.fromString(split[1]);
        boolean pm = split[2].equals("PM");
         
        return new KenpoTime(hour, min, pm);
    }
}
