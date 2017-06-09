package web.template.controller;

import bean.template.UserDO;
import bean.template.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangsx on 2017/6/9.
 */
@Controller("/common")
public class CommonController {
    public ModelAndView login(UserVO userVO){
        ModelAndView mav=new ModelAndView("index");
        UserDO userDO=new UserDO();
        mav.addObject("user",userDO);
        return mav;
    }
}
