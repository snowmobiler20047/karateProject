/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule.data;

import java.util.Collection;
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
    private Collection<Lesson> lessons;
}
