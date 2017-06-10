package web.template.controller;
import bean.template.UserVO;
import bean.template.exception.PasswdException;
import bo.template.PermissionBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangsx on 2017/6/9.
 */
@Controller()
@RequestMapping("/common")
public class CommonController {

    private PermissionBO permissionBO;

    @RequestMapping(value="/login.do",method = RequestMethod.POST)
    public ModelAndView login(UserVO userVO) throws PasswdException {
        permissionBO.login(userVO);
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
}
