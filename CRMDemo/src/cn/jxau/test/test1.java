package cn.jxau.test;

import cn.jxau.dao.*;
import cn.jxau.pojo.CrmClass;
import cn.jxau.pojo.Lesson;
import cn.jxau.pojo.Staff;
import cn.jxau.vo.ClassVo;
import cn.jxau.vo.StudentVo;

import java.util.List;

public class test1 {
    public static void main(String[] args) {
//        ClassDao classDao = new ClassDao();
//        List<ClassVo> classVos = classDao.selByPage(0, 5);
//        System.out.println(classVos);
//        ClassDao classDao = new ClassDao();
//        CrmClass crmClass = new CrmClass();
//        crmClass.setClassName("大数据");
//        crmClass.setLessonTypeID(3);
//        crmClass.setBeginTime("2018-08-31 00:00:00");
//        crmClass.setEndTime("2018-12-31 00:00:00");
//        crmClass.setRemark("你猜呀!");
//        long total = classDao.total();
//        System.out.println(total);
//        int insert = classDao.insert(crmClass);
//        System.out.println(insert);
//        CrmClass crmClass1 = classDao.selById(2);
//        System.out.println(crmClass);
//        int i = classDao.delById(10);
//        System.out.println(i);
//        StaffDao staffDao = new StaffDao();
//        int i = staffDao.selByLoginName("lichangying");
//        System.out.println(i);
//        ClassDao classDao = new ClassDao();
//        List<ClassVo> classVos = classDao.selByCondition("已结束", "", "",0,7);
//        System.out.println(classVos);
//        LessonDao lessonDao  = new LessonDao();
//        Lesson lesson = lessonDao.selById(1);
//        System.out.println(lesson);
//        Lesson lesson1 = new Lesson();
//        lesson1.setLessonCost(9999);
//        lesson1.setLessonName("高级算法");
//        lesson1.setRemark("我不猜");
//        short s = 2;
//        lesson1.setTotalTime(s);
//        lesson1.setId(11);
//        int i = lessonDao.updateById(lesson1);
//        System.out.println(i);
//        List<Lesson> lessons = lessonDao.selByPage(0, 6);
//        System.out.println(lessons);
        IStudentDao iStudentDao = new StuDao();


    }
}
