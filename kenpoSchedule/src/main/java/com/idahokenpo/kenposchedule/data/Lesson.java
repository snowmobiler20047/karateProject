package com.idahokenpo.kenposchedule.data;

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
    private Set<Student> students;
//    private Double cost;
    
    public double calculateCost()
    {
        if(students == null || students.isEmpty())
            return 0d;
        
        double cost = 0d;
        
        for (Student student : students)
        {
            
        }
        return cost;
    }
}
