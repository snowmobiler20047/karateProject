package com.idahokenpo.kenposchedule.data;

import java.util.Set;

/**
 *
 * @author Korey
 */
public class Instructor extends Person
{
    private Set<Student> students;
    private LessonCost lessonCost;
}
