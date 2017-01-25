package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Sets;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author Korey
 */
@Data
public class Lesson 
{
    private LessonType lessonType;
    private LessonStatus status;
    private Instructor instructor;
    private Set<Student> students = Sets.newHashSet();
    
    public double calculateCost()
    {
        if(students == null || students.isEmpty() || lessonType.equals(LessonType.GROUP))
            return 0d;
        
        double cost = 0d;
        boolean first = true;
        for (Student student : students)
        {
            if (first)
            {
                cost += instructor.getLessonCost().getCost();
                first = false;
            }
            else
                cost += 5d;
        }
        return cost;
    }
    
//    private double applyDiscount(double baseLessonCost)
//    {
//        return baseLessonCost - (baseLessonCost * 0.25); 
//    }
}
