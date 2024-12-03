package service;

import dao.LessonDaoImpl;
import models.DayOfWeek;
import models.Lesson;
import models.WeekType;
import utils.DBConnector;

import java.util.List;

public class LessonsServiceImpl implements LessonsService {
    private LessonDaoImpl lessonDao;

    public LessonsServiceImpl() {
        this.lessonDao = new LessonDaoImpl(new DBConnector().getConnection());
    }

    @Override
    public List<Lesson> getLessonsByParams(DayOfWeek dayOfWeek, WeekType weekType, int group) {
        return lessonDao.getLessonsListByParams(dayOfWeek, weekType, group);
    }

    @Override
    public boolean createLesson(Lesson lesson) {
        return lessonDao.add(lesson);
    }

    @Override
    public boolean updateLesson(int id, Lesson lesson) {
        return lessonDao.edit(id, lesson);
    }

    @Override
    public boolean deleteLesson(int id) {
        return lessonDao.remove(id);
    }

    @Override
    public List<Lesson> getLessons() {
        return lessonDao.getList();
    }

    @Override
    public Lesson getLessonById(int id) {
        return lessonDao.getById(id);
    }
}
