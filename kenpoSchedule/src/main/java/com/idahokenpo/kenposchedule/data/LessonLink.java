package com.idahokenpo.kenposchedule.data;

import com.google.common.collect.Sets;
import java.util.Set;

/**
 * A class to represent linked lessons that will be used to calculate discounts for the
 * additional lessons.
 * @author Korey
 */
public class LessonLink
{
    private final Lesson primaryLesson;
    private final Set<Lesson> additionalLessons;

    private LessonLink(Lesson primaryLesson, Set<Lesson> additionalLessons)
    {
        this.primaryLesson = primaryLesson;
        this.additionalLessons = additionalLessons;
    }

    public Lesson getPrimaryLesson()
    {
        return primaryLesson;
    }
    
    public Set<Lesson> getAdditionalLessons()
    {
        return additionalLessons;
    }
    
    public class LessonLinkBuilder
    {
        private final Lesson primaryLesson;
        private Set<Lesson> additionalLessons;

        public LessonLinkBuilder(Lesson primaryLesson, Lesson additionalLesson)
        {
            this.primaryLesson = primaryLesson;
            additionalLessons = Sets.newHashSet(additionalLesson);
        }
        
        public LessonLink build()
        {
            return new LessonLink(primaryLesson, additionalLessons);
        }
        
        public LessonLinkBuilder withAdditionalLesson(Lesson lesson)
        {
            additionalLessons.add(lesson);
            return this;
        }
    }
}
