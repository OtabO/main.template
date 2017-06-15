package web.template.support;
import bo.template.PermissionBO;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {

    private PermissionBO permissionBO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Object user=session.getAttribute("user");
        if(user==null){
            response.sendRedirect("/htm/login.htm");
            return false;
        }
        return true;
    }

    public void setPermissionBO(PermissionBO permissionBO) {
        this.permissionBO = permissionBO;
    }
}