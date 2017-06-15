package web.template.controller;

import api.template.Result;
import bean.template.UserDO;
import bean.template.UserVO;
import bean.template.constant.TemplateConstant;
import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;
import bo.template.PermissionBO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by zhangsx on 2017/6/9.
 */
@Controller()
@RequestMapping("/common")
public class CommonController {

    private PermissionBO permissionBO;

    private HttpSession httpSession;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public
    @ResponseBody
    Result login(UserVO userVO) throws PasswdException, NonUserException {
        Result result = new Result();
        try {
            UserDO userDO = permissionBO.login(userVO);
            result.setSuccess(true);
            httpSession.setAttribute(TemplateConstant.SESSION_USER_KEY,userDO);
            return result;
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            result.setSuccess(false);
            return result;
        }
    }

    @RequestMapping(value = "/index.do")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping(value = "/error404.do")
    public ModelAndView error404() {
        ModelAndView mav = new ModelAndView("error404");
        return mav;
    }

    @RequestMapping(value = "/nonprivileged.do")
    public ModelAndView nonprivileged() {
        ModelAndView mav = new ModelAndView("nonprivileged");
        return mav;
    }

    public void setPermissionBO(PermissionBO permissionBO) {
        this.permissionBO = permissionBO;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

}
