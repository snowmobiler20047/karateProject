package com.idahokenpo.kenposchedule.data;

/**
 *
 * @author Korey
 */
public enum KenpoHour
{
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    ELEVEN("11"),
    TWELVE("12");
    
    private String displayValue;

    private KenpoHour(String displayValue)
    {
        this.displayValue = displayValue;
    }

    public String getDisplayValue()
    {
        return displayValue;
    }
    
    public static KenpoHour fromString(String s)
    {
        for (KenpoHour value : values())
        {
            if (s.equals(value.getDisplayValue()))
                return value;
        }
        throw new IllegalArgumentException(s + " is not found in " + KenpoHour.class.getSimpleName());
    }
}
