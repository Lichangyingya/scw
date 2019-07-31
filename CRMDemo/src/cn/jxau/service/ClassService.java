package cn.jxau.service;

import cn.jxau.pojo.CrmClass;
import cn.jxau.pojo.PageInfo;
import cn.jxau.vo.ClassVo;

import java.util.List;

public interface ClassService {
    PageInfo showPage(int pageNumber,int pageSize);

    ClassVo getClass(int id);

    CrmClass selById(int id);

    int delClass(int id);

    int addClass(CrmClass crmClass);

    int update(CrmClass crmClass);

    int isExistClass(String className);

    PageInfo selByCondition(String status,String beginTime,String endTime,int pageNumber,int pageSize);

    List<CrmClass> getAllClass();

    int uploadFile(String uploadFilName,String uploadPath,int id);

    List<CrmClass> getClasses();



}
