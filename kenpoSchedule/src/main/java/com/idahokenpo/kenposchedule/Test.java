package com.idahokenpo.kenposchedule;

import com.google.gson.Gson;
import com.idahokenpo.kenposchedule.dao.InstructorDao;
import com.idahokenpo.kenposchedule.dao.StudentDao;
import com.idahokenpo.kenposchedule.dao.WeeklyScheduleDao;
import com.idahokenpo.kenposchedule.data.BeltRank;
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
import com.idahokenpo.kenposchedule.data.WeekIdentifier;
import com.idahokenpo.kenposchedule.data.serialization.SerializationUtils;
import java.time.DayOfWeek;

/**
 *
 * @author Korey
 */
public class Test
{

    public static void main(String[] args)
    {
//        doTest();
//        doDatabaseTest();
        doDatabaseTest2();
//        doBillingTest();

    }

    private static void doTest()
    {
        Schedule schedule = new Schedule();
        KenpoTime startTime = new KenpoTime(KenpoHour.FIVE, KenpoMinute.FORTY_FIVE, false);
        KenpoTime endTime = new KenpoTime(KenpoHour.SIX, KenpoMinute.THIRTY, false);
        TimeSlot timeSlot = new TimeSlot(startTime, endTime);

        TimeSlot otherTimeSlot = new TimeSlot(new KenpoTime(KenpoHour.SIX, KenpoMinute.THIRTY, false), new KenpoTime(KenpoHour.SEVEN, KenpoMinute.ZERO, false));

        schedule.getWeeklyScheduleMap().get(new WeekIdentifier()).getDayToTimeslotsMap().get(DayOfWeek.THURSDAY).add(timeSlot);
        schedule.getWeeklyScheduleMap().get(new WeekIdentifier()).getDayToTimeslotsMap().get(DayOfWeek.THURSDAY).add(otherTimeSlot);

        Instructor instructor = new Instructor();
        instructor.setPrefix("Mr");
        instructor.setFirstName("First");
        instructor.setLastName("Last");
        instructor.setLessonCost(LessonCost.NORMAL_PRIVATE);
        instructor.setSchedule(schedule);

        Lesson lesson = new Lesson();
//        lesson.setInstructor(instructor);
        lesson.setLessonType(LessonType.GROUP);
        lesson.addStudent(new Student());
        lesson.setStatus(LessonStatus.READY);

        Lesson lesson2 = new Lesson();
//        lesson2.setInstructor(instructor);
        lesson2.setLessonType(LessonType.PRIVATE);
        lesson2.setStatus(LessonStatus.READY);
        lesson2.getStudents().add(new Student());

        timeSlot.setLesson(lesson);
        otherTimeSlot.setLesson(lesson2);

//        for (Map.Entry<WeekIdentifier, WeeklySchedule> weeklyEntry : schedule.getWeeklyScheduleMap().entrySet())
//        {
//            System.out.println(weeklyEntry.getKey());
//            WeeklySchedule weeklySchedule = weeklyEntry.getValue();
//
//            for (Map.Entry<DayOfWeek, NavigableSet<TimeSlot>> entry : weeklySchedule.getDayToTimeslotsMap().entrySet())
//            {
//                System.out.println(entry.getKey());
//                for (TimeSlot timeSlot1 : entry.getValue())
//                {
//                    System.out.println("\t"
//                            + timeSlot1.getStartTime().toString()
//                            + " - "
//                            + timeSlot1.getEndTime().toString()
//                            + " "
//                            + instructor.getNickname()
//                            + " "
//                            + timeSlot1.getLesson().getLessonType()
//                            + " "
//                            + timeSlot1.getLesson().getStatus()
//                            + " Cost:$"
//                            + timeSlot1.getLesson().calculateCost(instructor));
//                }
//            }
//        }
    }

    private static void doDatabaseTest()
    {
//        DataLoader loader = new DataLoader();
//        loader.createDatabase();
        StudentDao loader = new StudentDao();
        //loader.deleteStudents();
        Student student = new Student();
        student.setFirstName("Kat");
        student.setLastName("Dogg");
        student.setRank(BeltRank.SECOND_BLACK);
//        loader.insertStudent(student);

        //List<Student> students = loader.getStudents();
//        Student student2 = loader.getStudent(students.get(0).getPersonId());
//        student2.setRank(BeltRank.BLUE);
//        student2.setFirstName("changedFirst");
//        student2.setLastName("changedLastname");
//        System.out.println("Update " + student2);
//        loader.updateStudent(student2);
        for (Student s : loader.getStudents())
        {
            System.out.println(s);
        }
    }

    private static void doBillingTest()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void doDatabaseTest2()
    {
        Gson gson = SerializationUtils.getGson();
//        String json = gson.toJson(new WeekIdentifier());
//        WeekIdentifier weekId = gson.fromJson(json, WeekIdentifier.class);
//        System.out.println(json + "\n" + weekId);
        
//        //map
//        NavigableMap<WeekIdentifier, WeeklySchedule> weeklyScheduleMap = Maps.newTreeMap();
//        weeklyScheduleMap.put(weekId, new WeeklySchedule());
//        json = gson.toJson(weeklyScheduleMap);
//        System.out.println(json); 
//        System.out.println(gson.fromJson(json, NavigableMap.class));
        
        
        
        //schedule
//        json = gson.toJson(new Schedule());
//        System.out.println(json);
//        Schedule scheduleFromJson = gson.fromJson(json, Schedule.class);
//        System.out.println(json + "\n" + scheduleFromJson);
        
        Schedule schedule = new Schedule();
        KenpoTime startTime = new KenpoTime(KenpoHour.FIVE, KenpoMinute.FORTY_FIVE, false);
        KenpoTime endTime = new KenpoTime(KenpoHour.SIX, KenpoMinute.THIRTY, false);
        TimeSlot timeSlot = new TimeSlot(startTime, endTime);

        TimeSlot otherTimeSlot = new TimeSlot(new KenpoTime(KenpoHour.SIX, KenpoMinute.THIRTY, false), new KenpoTime(KenpoHour.SEVEN, KenpoMinute.ZERO, false));

        WeekIdentifier weekId = new WeekIdentifier();
        schedule.getWeeklyScheduleMap().get(weekId).getDayToTimeslotsMap().get(DayOfWeek.THURSDAY).add(timeSlot);
        schedule.getWeeklyScheduleMap().get(weekId).getDayToTimeslotsMap().get(DayOfWeek.THURSDAY).add(otherTimeSlot);

        WeeklyScheduleDao scheduleDao = new WeeklyScheduleDao();
        scheduleDao.insert(schedule.getWeeklyScheduleMap().get(weekId));
        
        
        Instructor instructor = new Instructor();
        instructor.setPrefix("Ms");
        instructor.setFirstName("First");
        instructor.setLastName("Wu");
        instructor.setLessonCost(LessonCost.NORMAL_PRIVATE);
        instructor.setSchedule(schedule);

        Lesson lesson = new Lesson();
//        lesson.setInstructor(instructor);
        lesson.setLessonType(LessonType.GROUP);
        lesson.addStudent(new Student());
        lesson.setStatus(LessonStatus.READY);

        Lesson lesson2 = new Lesson();
//        lesson2.setInstructor(instructor);
        lesson2.setLessonType(LessonType.PRIVATE);
        lesson2.setStatus(LessonStatus.READY);
        lesson2.getStudents().add(new Student());

        timeSlot.setLesson(lesson);
        otherTimeSlot.setLesson(lesson2);
        
        InstructorDao dao = new InstructorDao();
//        dao.clear();
        dao.insertInstructor(instructor);
        for (Instructor instructor1 : dao.getInstructors())
        {
            System.out.println(instructor1);
        }
    }
}
