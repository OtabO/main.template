package web.template.controller;

import bean.template.UserDO;
import bean.template.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangsx on 2017/6/9.
 */
@Controller("/common")
public class CommonController {
    @RequestMapping(value="/login")
    public ModelAndView login(){
        ModelAndView mav=new ModelAndView("login");
        return mav;
    }
}
