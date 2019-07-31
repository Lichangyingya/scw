package cn.jxau.service.Impl;

import cn.jxau.dao.LessonDao;
import cn.jxau.pojo.Lesson;
import cn.jxau.pojo.PageInfo;
import cn.jxau.service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {

    private LessonDao lessonDao = new LessonDao();

    /**
     * 得到所有课程
     * @return
     */
    @Override
    public List<Lesson> getAll() {
        return lessonDao.getAll();
    }

    @Override
    public PageInfo showPage(int pageNumber, int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<Lesson> lessons = lessonDao.selByPage(pageStart, pageSize);
        long total = lessonDao.selTotal();
        long totalPage = total%pageSize==0? total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo(pageSize,pageNumber,totalPage,total,lessons,pageStart);
        return pageInfo;
    }

    @Override
    public Lesson getLesson(int id) {
        return lessonDao.selById(id);
    }

    @Override
    public int deleteLesson(int id) {
        return lessonDao.delById(id);
    }

    @Override
    public int addLesson(Lesson lesson) {
        return lessonDao.insLesson(lesson);
    }

    @Override
    public int updateLesson(Lesson lesson) {
        return lessonDao.updateById(lesson);
    }


}
