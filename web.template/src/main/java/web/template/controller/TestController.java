package web.template.controller;

import bean.template.exception.PasswdException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zsx on 2017/6/8.
 */
@Controller
public class TestController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public ModelAndView hello(){
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", new PasswdException());
        ModelAndView mav=new ModelAndView("login",model);
        return mav;
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String index1(){
        throw new RuntimeException();
    }
}
