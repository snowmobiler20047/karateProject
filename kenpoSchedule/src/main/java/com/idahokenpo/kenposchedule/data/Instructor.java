package com.idahokenpo.kenposchedule.data;

import java.util.Set;
import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class Instructor extends Person
{
    private Set<Student> students;
    private LessonCost lessonCost;
    private Schedule schedule;
}
