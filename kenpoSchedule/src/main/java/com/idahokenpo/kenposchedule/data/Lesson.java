package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import org.bson.types.ObjectId;

/**
 *
 * @author Korey
 */
@Data
public class Lesson
{
    private String lessonId;
    private LessonType lessonType;
    private LessonStatus status;
    private Set<Student> students = Sets.newHashSet();
    private String accountId;
    private LessonLink lessonLink;

    public Lesson(LessonType lessonType)
    {
        this.lessonId = new ObjectId().toHexString();
        this.lessonType = lessonType;
        this.status = LessonStatus.READY;
    }
    
    
    public Lesson(Lesson lesson)
    {
        this.lessonId = lesson.getLessonId();
        this.lessonType = lesson.getLessonType();
        this.status = LessonStatus.READY;
        this.students = new HashSet<>(lesson.getStudents());
        this.accountId = lesson.getAccountId();
    }

    public boolean addStudent(Student student)
    {
        return students.add(student);
    }
    
    public boolean removeStudent(Student student)
    {
        return students.remove(student);
    }

    public double calculateCost(Instructor instructor)
    {
        if (students == null || students.isEmpty())
        {
            return 0d;
        }

        if (lessonType.equals(LessonType.GROUP))
        {
            return LessonCost.GROUP_ONLY.getCost();
        }

        double cost = 0d;
        boolean first = true;
        for (Student student : students)
        {
            if (first)
            {
                if (lessonLink != null && !lessonLink.getPrimaryLesson().getLessonId().equals(this.getLessonId()))
                {
                    cost += applyDiscount(instructor.getLessonCost().getCost());
                } else
                {
                    cost += instructor.getLessonCost().getCost();
                }
                first = false;
            } else
            {
                cost += LessonCost.ADDITIONAL_STUDENT.getCost();
            }
        }
        return cost;
    }

    private double applyDiscount(double baseLessonCost)
    {
        return baseLessonCost - (baseLessonCost * 0.25); 
    }
}
