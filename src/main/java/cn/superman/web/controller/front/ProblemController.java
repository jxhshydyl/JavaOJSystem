package cn.superman.web.controller.front;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import cn.superman.web.dto.CodeDTO;
import cn.superman.web.dto.MyRecord;
import org.apache.xmlbeans.impl.xb.ltgfmt.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.superman.constant.ConstantParameter;
import cn.superman.util.BeanMapperUtil;
import cn.superman.web.bean.ResponseMap;
import cn.superman.web.constant.WebConstant;
import cn.superman.web.controller.PageController;
import cn.superman.web.dto.ProblemAnswerDTO;
import cn.superman.web.exception.ServiceLogicException;
import cn.superman.web.po.Problem;
import cn.superman.web.po.User;
import cn.superman.web.service.front.AnswerSubmitService;
import cn.superman.web.service.front.ProblemService;
import cn.superman.web.service.page.PageResult;
import cn.superman.web.service.page.PageService;
import cn.superman.web.vo.request.ProblemAnswerVO;
import cn.superman.web.vo.request.ProblemSearchByDifficultyVO;
import cn.superman.web.vo.request.ProblemSearchByNameVO;
import cn.superman.web.vo.request.ProblemSearchByTypeVO;
import cn.superman.web.vo.request.ProblemSearchByValueVO;
import cn.superman.web.vo.response.ProblemResponse;

@Controller
@RequestMapping("/ProblemController")
public class ProblemController{
    @Autowired
    private ProblemService problemService;
    @Autowired
    private AnswerSubmitService answerSubmitService;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMap find(@RequestParam("id") Integer id) {
        ResponseMap responseMap = new ResponseMap().buildSucessResponse();
        responseMap.append("problem", problemService.getProblemById(id));
        return responseMap;
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<CodeDTO> list(@RequestParam("pageShowCount") int currentPage, @RequestParam("pageShowCount") int pageShowCount, @RequestParam("wantPageNumber") int wantPageNumber) {
        return problemService.getList(currentPage,pageShowCount,wantPageNumber);
    }
    /**
     * 默认进行题目名字模糊查找
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/searchByName", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<Problem> searchByName(@Valid ProblemSearchByNameVO vo) {
        return problemService.getPageByLikeName(vo.getPageShowCount(), vo.getWantPageNumber(), vo.getProblemName());
    }

    @RequestMapping(value = "/searchByType", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<Problem> searchByType(@Valid ProblemSearchByTypeVO vo) {
        Problem condition = new Problem();
        condition.setIsPublish(true);
        condition.setProblemTypeId(vo.getProblemTypeId());
        return problemService.getPage(vo.getPageShowCount(), vo.getWantPageNumber(), condition);
    }

    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<Problem> search(@PathVariable Integer id) {
        System.out.println(id+"==============");
        if(id!=-1){
            Problem condition = new Problem();
            condition.setProblemId(id);
            condition.setIsPublish(true);
            return problemService.getPage(1, 1, condition);
        }
        return problemService.getPageByLikeName(1, 10, null);
    }

    @RequestMapping(value = "/searchByValue", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<Problem> searchByValue(@Valid ProblemSearchByValueVO vo) {
        Problem condition = new Problem();
        condition.setIsPublish(true);
        condition.setProblemValue(vo.getProblemValue());
        return problemService.getPage(vo.getPageShowCount(), vo.getWantPageNumber(), condition);
    }

    @RequestMapping(value = "/searchByDifficulty", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<Problem> searchByDifficulty(@Valid ProblemSearchByDifficultyVO vo) {
        Problem condition = new Problem();
        condition.setIsPublish(true);
        condition.setProblemDifficulty(vo.getProblemDifficulty());
        return problemService.getPage(vo.getPageShowCount(), vo.getWantPageNumber(), condition);
    }

    @RequestMapping(value = "/myRecord/{problemId}", method = RequestMethod.GET)
    @ResponseBody
    public MyRecord queryMyRecord(@PathVariable("problemId") Integer problemId,HttpSession session) {
        //得到登录的用户
        User user = (User) session.getAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME);
        MyRecord myRecord = answerSubmitService.queryMyRecord(user,problemId);
        return myRecord ;
    }


    @RequestMapping(value = "/evaluateStatistics/{problemId}", method = RequestMethod.GET)
    @ResponseBody
    public PageResult<Problem> queryEvaluateStatistics(@PathVariable Integer problemId,HttpSession session) {
        //得到登录的用户
        User user = (User) session.getAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME);
        System.out.println(user);
        System.out.println(problemId);
        return null;
    }

    @RequestMapping(value = "/submitAnswer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMap submitAnswer(HttpSession session, @Valid ProblemAnswerVO problemAnswerVO) {
        Long nextSubmitTime = (Long) session.getAttribute(ConstantParameter.SUBMIT_RECORD_TOKEN_NAME);
        System.out.println(problemAnswerVO);
        // 如果为空，就表明是第一次提交
        if (nextSubmitTime != null) {
            if (nextSubmitTime.longValue() > System.currentTimeMillis()) {
                throw new ServiceLogicException("请" + TimeUnit.MILLISECONDS.toSeconds(nextSubmitTime.longValue() - System.currentTimeMillis()) + "秒后再提交代码");
            }
        }

        User user = (User) session.getAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME);
        System.out.println(user);
        ProblemAnswerDTO dto = BeanMapperUtil.map(problemAnswerVO, ProblemAnswerDTO.class);
        dto.setUser(user);
        String message=answerSubmitService.dealCode(dto);
        System.out.println(message);
        // 5秒后才能允许再一次提交代码
        session.setAttribute(ConstantParameter.SUBMIT_RECORD_TOKEN_NAME, System.currentTimeMillis() + ConstantParameter.SUBMIT_RECORD_GAP);
        // TODO 返回个人历史提交显示页面
        ResponseMap responseMap = new ResponseMap();
        if(message != null && message != ""){
            if(message.indexOf("测试数据通过率：100")==-1){
                responseMap.append("message",message);
            }else{
                responseMap.append("message","测试通过");
            }
        }else{
            responseMap.append("message","请重新提交！");
        }
        return responseMap;
    }

}
