package cn.superman.web.service.front;

import cn.superman.web.dao.ClassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    @Autowired
    private ClassDao classDao;

    public String queryClassPeopleCountAndMakeProblem(){
        classDao.queryClassPeopleCountAndMakeProblem();
        return null;
    }
}
