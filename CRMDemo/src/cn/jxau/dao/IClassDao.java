package cn.jxau.dao;

import cn.jxau.pojo.CrmClass;
import cn.jxau.vo.ClassVo;

public interface IClassDao {
    ClassVo selById(int id);

    CrmClass selClass(int id);

}
