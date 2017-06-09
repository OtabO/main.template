package web.template;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zsx on 2017/6/8.
 */
@Controller
public class TestController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public @ResponseBody String hello(){
        return "hello";
    }

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String index1(){
        return "error500";
    }
}
