package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Sets;
import com.idahokenpo.kenposchedule.dao.LessonDao;
import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent linked lessons that will be used to calculate discounts for the
 * additional lessons.
 * @author Korey
 */
public class LessonLink
{
    private final String primaryLessonId;
    private final Set<String> additionalLessonIds;
    
    transient boolean loaded = false;
    transient Lesson primaryLesson;
    transient Set<Lesson> additionalLessons = new HashSet();

    private LessonLink(String primaryLessonId, Set<String> additionalLessonIds)
    {
        this.primaryLessonId = primaryLessonId;
        this.additionalLessonIds = additionalLessonIds;
    }
    
//    private LessonLink(Lesson primaryLesson, Set<Lesson> additionalLessons)
//    {
//        this.primaryLesson = primaryLesson;
//        this.additionalLessons = additionalLessons;
//    }

    public Lesson getPrimaryLesson()
    {
        if (loaded)
            load();
        
        return primaryLesson;
    }
    
    public Set<Lesson> getAdditionalLessons()
    {
        
        if (loaded)
            load();
        
        return additionalLessons;
    }
    
    public void load()
    {
        LessonDao lessonDao = new LessonDao();
        this.primaryLesson = lessonDao.get(primaryLessonId);
        
        this.additionalLessons.addAll(lessonDao.get(additionalLessonIds));
                
        loaded = true;
    }
    
    public class LessonLinkBuilder
    {
        private final String primaryLessonId;
        private final Set<String> additionalLessonIds;

        public LessonLinkBuilder(String primaryLessonId, String additionalLessonId)
        {
            this.primaryLessonId = primaryLessonId;
            this.additionalLessonIds = Sets.newHashSet(additionalLessonId);
        }
        
        public LessonLink build()
        {
            return new LessonLink(primaryLessonId, additionalLessonIds);
        }
        
        public LessonLinkBuilder withAdditionalLesson(String lessonId)
        {
            this.additionalLessonIds.add(lessonId);
            return this;
        }
    }
}
