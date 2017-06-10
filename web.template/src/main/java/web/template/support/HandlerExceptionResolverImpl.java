package web.template.support;

import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;
import bean.template.exception.PermissionException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class HandlerExceptionResolverImpl implements HandlerExceptionResolver {
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);
        if(ex instanceof NonUserException) {
            return new ModelAndView("", model);
        }else if(ex instanceof PermissionException) {
            return new ModelAndView("", model);
        } else if(ex instanceof PasswdException){
            try {
                response.sendRedirect("/htm/login.htm");

            } catch (IOException e) {

            }
        } else{
            return new ModelAndView("error500", model);
        }
        return null;
    }
}
