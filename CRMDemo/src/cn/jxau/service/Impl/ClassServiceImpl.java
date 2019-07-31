package cn.jxau.service.Impl;

import cn.jxau.dao.ClassDao;
import cn.jxau.pojo.CrmClass;
import cn.jxau.pojo.PageInfo;
import cn.jxau.service.ClassService;
import cn.jxau.vo.ClassVo;

import java.util.List;

public class ClassServiceImpl implements ClassService {
    private ClassDao classDao = new ClassDao();

    @Override
    public List<CrmClass> getAllClass() {
        return classDao.selAllStatus();
    }

    @Override
    public PageInfo selByCondition(String status, String beginTime, String endTime,int pageNumber,int pageSize) {
        int pageStart = (pageNumber-1)*pageSize;
        List<ClassVo> classVos = classDao.selByCondition(status, beginTime, endTime, pageStart, pageSize);
        long total = classDao.totalByCondition(status, beginTime, endTime);
        long totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageStart(pageStart);
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setTotal(total);
        pageInfo.setTotalPage(totalPage);
        pageInfo.setList(classVos);
        return pageInfo;
    }

    /**
     * 分页显示  分页所有信息封装在PageInfo中
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo showPage(int pageNumber, int pageSize) {
        List<ClassVo> classVos = classDao.selByPage((pageNumber - 1) * pageSize, pageSize);
        long total = classDao.total();
        long totalPage=total%pageSize==0?total/pageSize:total/pageSize+1;
        PageInfo pageInfo = new PageInfo();
        pageInfo.setList(classVos);
        pageInfo.setTotal(total);
        pageInfo.setTotalPage(totalPage);
        pageInfo.setPageStart((pageNumber - 1) * pageSize);
        pageInfo.setPageNumber(pageNumber);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }

    @Override
    public CrmClass selById(int id) {
        return classDao.selClass(id);
    }

    /**
     * 得到所有班级
     * @return
     */
    @Override
    public List<CrmClass> getClasses() {
        return classDao.getClasses();
    }

    @Override
    public int uploadFile(String uploadFilName, String uploadPath,int id) {
        return classDao.updateFile(uploadFilName,uploadPath,id);
    }

    @Override
    public ClassVo getClass(int id) {
        return classDao.selById(id);
    }

    @Override
    public int delClass(int id) {
        return classDao.delById(id);
    }

    @Override
    public int addClass(CrmClass crmClass) {
        return classDao.insert(crmClass);
    }

    @Override
    public int isExistClass(String className) {
        return classDao.selByClassName(className);
    }

    @Override
    public int update(CrmClass crmClass) {
        return classDao.updateById(crmClass);
    }
}
