package cn.superman.web.controller.front;

import cn.superman.util.BeanMapperUtil;
import cn.superman.util.EncryptUtility;
import cn.superman.util.Log4JUtil;
import cn.superman.web.bean.ResponseMap;
import cn.superman.web.constant.WebConstant;
import cn.superman.web.controller.PageController;
import cn.superman.web.dto.*;
import cn.superman.web.exception.ServiceLogicException;
import cn.superman.web.po.Competition;
import cn.superman.web.po.CompetitionAccount;
import cn.superman.web.po.User;
import cn.superman.web.service.front.AnswerSubmitService;
import cn.superman.web.service.front.CompetitionApplicationService;
import cn.superman.web.service.front.CompetitionService;
import cn.superman.web.service.front.CorpusService;
import cn.superman.web.service.page.PageService;
import cn.superman.web.util.ThreadFactoryUtil;
import cn.superman.web.vo.request.BeginCompetitionVO;
import cn.superman.web.vo.request.CompetitionApplyVO;
import cn.superman.web.vo.request.CompetitionProblemAnswerVO;
import cn.superman.web.vo.response.CompetitionResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/corpusController")
public class CorpusController {
    @Autowired
    CorpusService corpusService;

    @RequestMapping("/getCorpus")
    @ResponseBody
     public ResponseMap getCorpus(){
        List<Corpus> corpus = corpusService.getCorpus();
        ResponseMap responseMap=new ResponseMap().buildSucessResponse().append("corpus",corpus);
        return responseMap;
     }

}
