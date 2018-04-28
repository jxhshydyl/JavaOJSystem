package cn.superman.web.controller.front;

import cn.superman.web.service.front.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping("/queryClassInfo")
    @ResponseBody
    public String queryClassPeopleCountAndMakeProblem(){
        classService.queryClassPeopleCountAndMakeProblem();
        return null;
    }
}
