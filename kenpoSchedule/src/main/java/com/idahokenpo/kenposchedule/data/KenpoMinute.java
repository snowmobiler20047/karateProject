/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.data;

/**
 *
 * @author Korey
 */
public enum KenpoMinute
{
    ZERO("00"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY_FIVE("45");
    
    private String minuteNum;

    private KenpoMinute(String minuteNum)
    {
        this.minuteNum = minuteNum;
    }
    
    public String getDisplayValue()
    {
        return minuteNum;
    }
    
    public static KenpoMinute fromString(String s)
    {
        for (KenpoMinute value : values())
        {
            if (value.getDisplayValue().equals(s))
                return value;
        }
        throw new IllegalArgumentException(s + " is not a valid input");
    }
}
