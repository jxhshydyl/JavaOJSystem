package cn.superman.web.dao;

import cn.superman.web.dao.base.MyBatisRepository;

@MyBatisRepository
public interface ClassDao {
    void queryClassPeopleCountAndMakeProblem();
}
