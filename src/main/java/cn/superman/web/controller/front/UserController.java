package cn.superman.web.controller.front;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.superman.util.BeanMapperUtil;
import cn.superman.web.bean.ResponseMap;
import cn.superman.web.bean.VerificationCode;
import cn.superman.web.constant.WebConstant;
import cn.superman.web.dto.UpdateUserPasswordDTO;
import cn.superman.web.dto.UserLeaderboardDTO;
import cn.superman.web.dto.UserLoginDTO;
import cn.superman.web.dto.UserRegisterDTO;
import cn.superman.web.dto.UserUpdateDTO;
import cn.superman.web.dto.VerificationCodeDTO;
import cn.superman.web.exception.ServiceLogicException;
import cn.superman.web.po.User;
import cn.superman.web.service.front.UserService;
import cn.superman.web.vo.request.UpdateUserPasswordVO;
import cn.superman.web.vo.request.UserLoginVO;
import cn.superman.web.vo.request.UserRegisterVO;
import cn.superman.web.vo.request.UserUpdateVO;
import cn.superman.web.vo.response.UserLoginResponse;

@Controller
@RequestMapping("/UserController")
public class UserController {
    private static final String VERIFICATION_CODE_ATTR_NAME = "verificationCode";

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param userRegisterVO
     * @param session
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMap registerUser(@Valid UserRegisterVO userRegisterVO, HttpSession session) {

        String code = (String) session.getAttribute(VERIFICATION_CODE_ATTR_NAME);
        if (StringUtils.isBlank(code)) {
            throw new ServiceLogicException("验证码失效，请刷新一次验证码");
        }

        if (!code.equals(userRegisterVO.getVerificationCode())) {
            throw new ServiceLogicException("验证码输入错误");
        }

        UserRegisterDTO dto = BeanMapperUtil.map(userRegisterVO, UserRegisterDTO.class);
        userService.register(dto);

        ResponseMap responseMap = new ResponseMap().buildSucessResponse();

        return responseMap;
    }

    /**
     * 判断帐号是否重复
     * @param account
     * @return
     */
    @RequestMapping(value = "/checkAccountIsUnique", method = RequestMethod.GET)
    public ResponseMap checkAccountIsUnique(@RequestParam(name = "account") String account) {
        userService.checkAccountIsUnique(account);

        ResponseMap responseMap = new ResponseMap().buildSucessResponse();

        return responseMap;
    }

    /**
     * 用户登录
     * @param userLoginVO
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMap loginUser(@Valid UserLoginVO userLoginVO, HttpServletRequest request) {
        UserLoginDTO dto = BeanMapperUtil.map(userLoginVO, UserLoginDTO.class);
        // TODO 以后，应该还有判断是否存在重复登录的情况
        User user = userService.login(dto);
        UserLoginResponse response = BeanMapperUtil.map(user, UserLoginResponse.class);
        request.getSession().setAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME, user);
        ResponseMap responseMap = new ResponseMap().buildSucessResponse();
        responseMap.append("user", response);
        return responseMap;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMap logout(HttpServletRequest request) {
        request.getSession().removeAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME);

        ResponseMap responseMap = new ResponseMap().buildSucessResponse();

        return responseMap;
    }

    /**
     * 更新验证码
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendUpdateCodeEmail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMap sendUpdateCodeEmail(HttpServletRequest request) {
        User user = getLoginUser(request);
        userService.sendUpdateCodeEmail(user);
        ResponseMap responseMap = new ResponseMap().buildSucessResponse();

        return responseMap;
    }

    /**
     * 认证学号
     * @param request
     * @return
     */
    @RequestMapping(value = "/sendConfirmEmail")
    @ResponseBody
    public ResponseMap sendConfirmEmail(HttpServletRequest request,String sno) {
        //User user = getLoginUser(request);
        System.out.println(sno);
        String s = userService.sendConfirmEmail(sno);
        ResponseMap responseMap;
        if("1".equals(s)){
            responseMap = new ResponseMap().buildSucessResponse();
        }else{
            responseMap = new ResponseMap().append("message","发送失败！");
        }

        return responseMap;
    }
    @RequestMapping(value = "/confirmSubmit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMap confirmSubmit(@Valid UserUpdateVO vo, HttpServletRequest request) {

        int confirm = userService.confirm(vo, getLoginUser(request).getUserId());
        ResponseMap responseMap;
        if (confirm==1) {
            User user = userService.find(getLoginUser(request).getUserId());
            UserLoginResponse response = BeanMapperUtil.map(user, UserLoginResponse.class);
            request.getSession().setAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME, user);
            responseMap = new ResponseMap().buildSucessResponse();
            responseMap.append("user", response);
        }else{
            responseMap=new ResponseMap().append("message","认证失败！");
        }


        return responseMap;
    }

    @RequestMapping(value = "/updateSubmit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMap updateSubmit(@Valid UserUpdateVO vo, HttpServletRequest request) {
        UserUpdateDTO dto = BeanMapperUtil.map(vo, UserUpdateDTO.class);
        dto.setUserId(getLoginUser(request).getUserId());

        userService.update(dto);

        User user = userService.find(dto.getUserId());
        UserLoginResponse response = BeanMapperUtil.map(user, UserLoginResponse.class);
        request.getSession().setAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME, user);
        ResponseMap responseMap = new ResponseMap().buildSucessResponse();
        responseMap.append("user", response);

        return responseMap;
    }

    /* 发送邮箱验证码  忘记密码*/
    @RequestMapping(value = "/sendForgetPasswordEmail/{account}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMap sendForgetPasswordEmail(@PathVariable("account") String account) {
        userService.sendForgetPasswordEmail(account);
        ResponseMap responseMap = new ResponseMap().buildSucessResponse();

        return responseMap;
    }

    /**
     *  忘记密码
     * @param vo
     * @param request
     * @return
     */
    @RequestMapping(value = "/updatePasswordSubmit", method = RequestMethod.POST)
    @ResponseBody
    public ResponseMap updatePasswordSubmit(@Valid UpdateUserPasswordVO vo, HttpServletRequest request) {
        UpdateUserPasswordDTO dto = BeanMapperUtil.map(vo, UpdateUserPasswordDTO.class);
        userService.updateUserPassword(dto);

        ResponseMap responseMap = new ResponseMap().buildSucessResponse();

        return responseMap;
    }

    /**
     * 排行榜
     * @return
     */
    @RequestMapping(value = "/leaderboard", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMap Leaderboard() {
        List<UserLeaderboardDTO> haveDoneProblemTop50 = userService.getHaveDoneProblemTop50();
        List<UserLeaderboardDTO> rightProblemTop50 = userService.getRightProblemTop50();
        List<UserLeaderboardDTO> sloveProblemTotalValueTop50 = userService.getSloveProblemTotalValueTop50();

        ResponseMap responseMap = new ResponseMap().buildSucessResponse();
        responseMap.append("haveDoneProblemTop50", haveDoneProblemTop50);
        responseMap.append("rightProblemTop50", rightProblemTop50);
        responseMap.append("sloveProblemTotalValueTop50", sloveProblemTotalValueTop50);

        return responseMap;
    }

    /**
     * 改变注册时的验证码
     * @param request
     * @return
     */
    @RequestMapping(value = "/changeVerificationCode", method = RequestMethod.GET)
    @ResponseBody
    public ResponseMap changeVerificationCode(HttpServletRequest request) {
        VerificationCodeDTO dto = getNewVerificationCode();
        request.getSession().setAttribute(VERIFICATION_CODE_ATTR_NAME, dto.getTextContent());

        ResponseMap responseMap = new ResponseMap().buildSucessResponse();

        responseMap.put("verificationCode", "data:image/jpg;base64," + dto.getImageData());
        return responseMap;
    }

    /**
     * 得到新的注册码
     * @return
     */
    private VerificationCodeDTO getNewVerificationCode() {
        VerificationCodeDTO dto = new VerificationCodeDTO();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerificationCode verificationCode = VerificationCode.getDefaultNewinstance();
        try {
            verificationCode.writeImgae(byteArrayOutputStream);
            dto.setTextContent(verificationCode.getText());
            dto.setImageData(Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()));
            return dto;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

    private User getLoginUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(WebConstant.USER_SESSION_ATTRIBUTE_NAME);
    }

}
