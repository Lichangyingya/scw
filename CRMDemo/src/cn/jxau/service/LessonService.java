package cn.jxau.service;

import cn.jxau.pojo.Lesson;
import cn.jxau.pojo.PageInfo;

import java.util.List;

public interface LessonService {
    List<Lesson> getAll();

    PageInfo showPage(int pageNumber,int pageSize);

    Lesson getLesson(int id);

    int deleteLesson(int id);

    int addLesson(Lesson lesson);

    int updateLesson(Lesson lesson);
}
