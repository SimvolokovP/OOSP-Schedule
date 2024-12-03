package service;

import models.DayOfWeek;
import models.Lesson;
import models.WeekType;

import java.util.List;

public interface LessonsService {
    List<Lesson> getLessonsByParams(DayOfWeek dayOfWeek, WeekType weekType, int group);
    boolean createLesson(Lesson lesson);
    boolean updateLesson(int id, Lesson lesson);
    boolean deleteLesson(int id);
    List<Lesson> getLessons();
    Lesson getLessonById(int id);
}
