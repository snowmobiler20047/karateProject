/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.idahokenpo.kenposchedule;

import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.KenpoHour;
import com.idahokenpo.kenposchedule.data.KenpoMinute;
import com.idahokenpo.kenposchedule.data.KenpoTime;
import com.idahokenpo.kenposchedule.data.Lesson;
import com.idahokenpo.kenposchedule.data.LessonCost;
import com.idahokenpo.kenposchedule.data.LessonStatus;
import com.idahokenpo.kenposchedule.data.LessonType;
import com.idahokenpo.kenposchedule.data.Schedule;
import com.idahokenpo.kenposchedule.data.Student;
import com.idahokenpo.kenposchedule.data.TimeSlot;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.NavigableSet;

/**
 *
 * @author Korey
 */
public class Test
{
    public static void main(String[] args)
    {
        
        Schedule schedule = new Schedule();
        KenpoTime startTime = new KenpoTime(KenpoHour.FIVE, KenpoMinute.FORTY_FIVE, false);
        KenpoTime endTime = new KenpoTime(KenpoHour.SIX, KenpoMinute.THIRTY, false);
        TimeSlot timeSlot = new TimeSlot(startTime, endTime);
        
        TimeSlot otherTimeSlot = new TimeSlot(new KenpoTime(KenpoHour.SIX, KenpoMinute.THIRTY, false), new KenpoTime(KenpoHour.SEVEN, KenpoMinute.ZERO, false));
        
        schedule.getDayToTimeslotMap().get(DayOfWeek.THURSDAY).add(timeSlot);
        schedule.getDayToTimeslotMap().get(DayOfWeek.THURSDAY).add(otherTimeSlot);
        
        Instructor instructor = new Instructor();
        instructor.setPrefix("Mr");
        instructor.setFirstName("First");
        instructor.setLastName("Last");
        instructor.setLessonCost(LessonCost.NORMAL_PRIVATE);
        instructor.setSchedule(schedule);
        
        Lesson lesson = new Lesson();
        lesson.setInstructor(instructor);
        lesson.setLessonType(LessonType.GROUP);
        lesson.setStatus(LessonStatus.READY);
        
        Lesson lesson2 = new Lesson();
        lesson2.setInstructor(instructor);
        lesson2.setLessonType(LessonType.PRIVATE);
        lesson2.setStatus(LessonStatus.READY);
        lesson2.getStudents().add(new Student());
        
        timeSlot.setLesson(lesson);
        otherTimeSlot.setLesson(lesson2);
        
        for (Map.Entry<DayOfWeek, NavigableSet<TimeSlot>> entry : schedule.getDayToTimeslotMap().entrySet())
        {
            System.out.println(entry.getKey());
            for (TimeSlot timeSlot1 : entry.getValue())
            {
                System.out.println("\t"
                        + timeSlot1.getStartTime().toString()
                        + " - "
                        + timeSlot1.getEndTime().toString()
                        + " "
                        + timeSlot1.getLesson().getInstructor().getNickname()
                        + " "
                        + timeSlot1.getLesson().getLessonType() 
                        + " " 
                        + timeSlot1.getLesson().getStatus()
                        + " Cost:$"
                        + timeSlot1.getLesson().calculateCost());   
            }
        }
        
        
        
    }
}
