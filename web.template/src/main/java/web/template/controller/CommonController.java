package web.template.controller;
import bean.template.UserDO;
import bean.template.UserVO;
import bean.template.constant.TemplateConstant;
import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;
import bo.template.PermissionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
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

    @RequestMapping(value="/login.do",method = RequestMethod.POST)
    public ModelAndView login(UserVO userVO) throws PasswdException, NonUserException {
        UserDO userDO=permissionBO.login(userVO);
        httpSession.setAttribute(TemplateConstant.SESSION_USER_KEY,userDO);
        ModelAndView mav=new ModelAndView("index");
        return mav;
    }

    @RequestMapping(value="/index.do")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView("index");
        return mav;
    }

    public void setPermissionBO(PermissionBO permissionBO) {
        this.permissionBO = permissionBO;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }
}
