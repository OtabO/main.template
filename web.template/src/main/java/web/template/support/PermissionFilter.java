package web.template.support;

import bean.template.UserDO;
import bean.template.constant.TemplateConstant;
import bo.template.PermissionBO;
import org.springframework.web.filter.OncePerRequestFilter;
import util.template.SpringContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangsx on 2017/6/10.
 */
public class PermissionFilter extends OncePerRequestFilter {

    private String excludePath;

    private List<String> excludePathList;

    public PermissionFilter() {

    }

    public PermissionFilter(String excludePath) {
        this.excludePath = excludePath;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if (excludePathList != null) {
            for (String path : excludePathList) {
                if (request.getServletPath().equals(path))
                    return true;
            }
        }
        return super.shouldNotFilter(request);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object user = session.getAttribute(TemplateConstant.SESSION_USER_KEY);
        if (user == null) {
            response.sendRedirect("/htm/login.htm");
            return;
        }
        if (!SpringContextHolder.getBean(PermissionBO.class).validateModule((UserDO) user, request.getServletPath())) {
            response.sendRedirect("/common/nonprivileged.do");
            return;
        }
        filterChain.doFilter(request, response);
    }

    public void setExcludePath(String excludePath) {
        this.excludePath = excludePath;
        if (excludePath != null) {
            String[] path = excludePath.split(",");
            if (path != null) {
                excludePathList = new ArrayList<>();
                for (String url : path) {
                    excludePathList.add(url);
                }
            }
        }
    }

}
