package web.template.support;

import bean.template.constant.TemplateConstant;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhangsx on 2017/6/10.
 */
public class PermissionFilter extends OncePerRequestFilter {

    private String excludePath;

    public PermissionFilter(){

    }

    public PermissionFilter(String excludePath){
        this.excludePath=excludePath;
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(excludePath!=null&&request.getServletPath().equals(excludePath)){
            return true;
        }
        return super.shouldNotFilter(request);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session=request.getSession();
        Object user=session.getAttribute(TemplateConstant.SESSION_USER_KEY);
        if(user==null){
            response.sendRedirect("/htm/login.htm");
            return;
        }
        filterChain.doFilter(request,response);
    }

    public void setExcludePath(String excludePath) {
        this.excludePath = excludePath;
    }
}
