package com.idahokenpo.kenposchedule;

import com.idahokenpo.kenposchedule.dao.InstructorDao;
import com.idahokenpo.kenposchedule.dao.LessonDao;
import com.idahokenpo.kenposchedule.dao.WeeklyScheduleDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import com.idahokenpo.kenposchedule.data.LessonType;
import com.idahokenpo.kenposchedule.data.WeekIdentifier;
import com.idahokenpo.kenposchedule.data.WeeklySchedule;
import java.time.DayOfWeek;
import java.util.NavigableMap;

/**
 *
 * @author Korey
 */
public class Controller
{
    private final InstructorDao instructorDao = new InstructorDao();
    private final WeeklyScheduleDao weeklyScheduleDao = new WeeklyScheduleDao();
    private final LessonDao lessonDao = new LessonDao();

    public WeeklySchedule createNextWeeklySchedule(String instructorId)
    {
        Instructor instructor = instructorDao.get(instructorId);
        
        return createNextWeeklySchedule(instructor);
    }
    
    public WeeklySchedule getNextWeeklySchedule(String instructorId, WeekIdentifier weekId)
    {
        Instructor instructor = instructorDao.get(instructorId);
        
        NavigableMap<WeekIdentifier, String> weeklyScheduleIdMap = instructor.getSchedule().getWeeklyScheduleIdMap();
        String nextWeekId = weeklyScheduleIdMap.get(new WeekIdentifier(weekId.getBillingDate().plusWeeks(1)));
        if (nextWeekId == null)
            return createNextWeeklySchedule(instructor);
                
        return weeklyScheduleDao.get(nextWeekId);     
    }
    
    private WeeklySchedule createNextWeeklySchedule(Instructor instructor) 
    {
        WeeklySchedule weeklySchedule = new WeeklySchedule(instructor.getPermenantSchedule());
        weeklyScheduleDao.insert(weeklySchedule);
        
        instructor.getSchedule().addNextWeek(weeklySchedule);
        instructorDao.update(instructor);
        return weeklySchedule;
    }

    public WeeklySchedule getPrevWeeklySchedule(String instructorId, WeekIdentifier weekId)
    {
        Instructor instructor = instructorDao.get(instructorId);
        
        NavigableMap<WeekIdentifier, String> weeklyScheduleIdMap = instructor.getSchedule().getWeeklyScheduleIdMap();
        String prevWeekId = weeklyScheduleIdMap.get(new WeekIdentifier(weekId.getBillingDate().minusWeeks(1)));
        if (prevWeekId == null)
            return createPrevWeeklySchedule(instructor);
                
        return weeklyScheduleDao.get(prevWeekId); 
    }
    
    private WeeklySchedule createPrevWeeklySchedule(Instructor instructor) 
    {
        WeeklySchedule weeklySchedule = new WeeklySchedule(instructor.getPermenantSchedule());
        weeklyScheduleDao.insert(weeklySchedule);
        
        instructor.getSchedule().addPrevWeek(weeklySchedule);
        instructorDao.update(instructor);
        return weeklySchedule;
    }
    
    public void addLesson(String weeklyScheduleId, DayOfWeek day, String timeSlotId, LessonType lessonType, String accountId)
    {
        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);   
     
        Lesson lesson = new Lesson(lessonType);
        lesson.setAccountId(accountId);
        weeklySchedule.addLesson(day, timeSlotId, lesson);
        
        weeklyScheduleDao.update(weeklySchedule);
        lessonDao.insert(lesson);
    }
    
    public Lesson removeLesson(String weeklyScheduleId, String lessonId)
    {
        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);
        return weeklySchedule.removeLesson(lessonId);
    }
}
