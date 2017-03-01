package com.idahokenpo.kenposchedule;

import com.idahokenpo.kenposchedule.dao.InstructorDao;
import com.idahokenpo.kenposchedule.dao.LessonDao;
import com.idahokenpo.kenposchedule.dao.StudentDao;
import com.idahokenpo.kenposchedule.dao.WeeklyScheduleDao;
import com.idahokenpo.kenposchedule.data.Instructor;
import com.idahokenpo.kenposchedule.data.Lesson;
import com.idahokenpo.kenposchedule.data.LessonType;
import com.idahokenpo.kenposchedule.data.Student;
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
    private final StudentDao studentDao = new StudentDao();

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
    
    public void addLesson(String weeklyScheduleId, DayOfWeek day, String timeSlotId, Lesson lesson)
    {
        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);
        
        weeklySchedule.addLesson(day, timeSlotId, lesson);

        weeklyScheduleDao.update(weeklySchedule);
    }

    public Lesson removeLesson(String weeklyScheduleId, String lessonId)
    {
        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);
        return weeklySchedule.removeLesson(lessonId);
    }

    public void addStudent(String weeklyScheduleId, String lessonId, String studentId)
    {
        Student student = studentDao.get(studentId);

        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);
        Lesson lesson = weeklySchedule.findLesson(lessonId);
        lesson.addStudent(student);

        weeklyScheduleDao.update(weeklySchedule);
    }

    public boolean removeStudent(String weeklyScheduleId, String lessonId, String studentId)
    {
        WeeklySchedule weeklySchedule = weeklyScheduleDao.get(weeklyScheduleId);
        Lesson lesson = weeklySchedule.findLesson(lessonId);
        Student student = studentDao.get(studentId);

        return lesson.removeStudent(student);
    }
    
    public void rescheduleLesson(String removeFromWeeklyScheduleId, String lessonId, String addToWeeklyScheduleId, DayOfWeek day, String timeSlotId)
    {
        Lesson lesson = removeLesson(removeFromWeeklyScheduleId, lessonId);
        
        addLesson(addToWeeklyScheduleId, day, timeSlotId, lesson);
    }
}
