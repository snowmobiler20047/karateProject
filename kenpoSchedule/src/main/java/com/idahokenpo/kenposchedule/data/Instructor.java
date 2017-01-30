package com.idahokenpo.kenposchedule.data;

import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class Instructor extends Person
{
    //private Set<Student> students; // why is this here?
    private LessonCost lessonCost;
    private Schedule schedule;
}
