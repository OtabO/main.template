package web.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangsx on 2017/6/15.
 */
@Controller()
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/list.do")
    public ModelAndView list() {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

}
