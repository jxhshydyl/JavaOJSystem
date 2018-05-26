package cn.superman.web.controller.front;

import cn.superman.system.sandbox.dto.Response;
import cn.superman.util.BeanMapperUtil;
import cn.superman.web.bean.ResponseMap;
import cn.superman.web.constant.WebConstant;
import cn.superman.web.dto.*;
import cn.superman.web.po.User;
import cn.superman.web.service.front.AnswerSubmitService;
import cn.superman.web.service.front.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RequestMapping("/taskController")
@Controller
public class TaskController {
    @Autowired
    TaskService taskService;
    @Autowired
    AnswerSubmitService answerSubmitService;

    @RequestMapping("/getTask")
    @ResponseBody
    public ResponseMap getTask(HttpServletRequest request,@Valid CourseDTO courseDTO){
        System.out.println(courseDTO);
        List<TaskDTO> task = taskService.getTask(courseDTO);
        ResponseMap response=new ResponseMap().buildSucessResponse().append("task",task);
        return response;
    }

    @RequestMapping("/getCourse")
    @ResponseBody
    public ResponseMap getCourse(HttpServletRequest request){
        List<CourseDTO> course = taskService.getCourse(getLoginUser(request).getIsStudent());
        ResponseMap response=new ResponseMap().buildSucessResponse().append("course",course);
        return response;
    }

    @RequestMapping("/getDetailTask")
    @ResponseBody
    public ResponseMap getDetailTask(HttpServletRequest request,String tid){
        List<CodeDTO> detailTask = taskService.getDetailTask(tid);
        ResponseMap response=new ResponseMap().buildSucessResponse().append("taskDetail",detailTask);
        return response;
    }

    @RequestMapping("/submitTaskProblemAnswer")
    @ResponseBody
    public ResponseMap submitTaskProblemAnswer(HttpSession session, @Valid SubmitTaskProblem submitTaskProblem){
        System.out.println(submitTaskProblem);
        ProblemAnswerDTO dto =new ProblemAnswerDTO();
        dto.setCode(submitTaskProblem.getCode());
        dto.setCodeLanguage(submitTaskProblem.getCodeType());
        dto.setSubmitProblemId(BigInteger.valueOf(submitTaskProblem.getProblemId()));
        dto.setCompetitionId(-1);
        dto.setCompetitionPeoblemNumber("-1");
        User user = (User) session.getAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME);
        dto.setUser(user);
        Map<String, Object> map = answerSubmitService.dealCode(dto, null);
        ResponseMap responseMap = new ResponseMap();
        taskService.insertStudentTask(submitTaskProblem,map,user);
        Map<String, Object> sim = taskService.getSim(submitTaskProblem, user);
        if(map.get("message") != null && map.get("message") != ""){
            if(((String)map.get("message")).indexOf("测试数据通过率：100") == -1){
                responseMap.append("message",map.get("message")).append("sim",sim);
            }else{
                responseMap.append("message","测试通过").append("sim",sim);
            }
        }else{
            responseMap.append("message","请重新提交！");
        }
        return responseMap;
    }

    private User getLoginUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME);
    }
}
