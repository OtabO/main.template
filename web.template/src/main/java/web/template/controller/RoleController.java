package web.template.controller;

import bean.template.RoleDO;
import bean.template.RolePermissionDO;
import bean.template.UserDO;
import dao.template.RoleDAO;
import dao.template.RolePermissionDAO;
import dao.template.UserDAO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zhangsx on 2017/10/31.
 */
@Controller()
@RequestMapping("/role")
public class RoleController {

    private UserDAO userDAO;

    private RoleDAO roleDAO;

    private RolePermissionDAO rolePermissionDAO;

    @RequestMapping(value = "/list.do")
    public ModelAndView list(UserDO searchData){
        ModelAndView mav=new ModelAndView("role");
        UserDO userInfo=userDAO.findByUserName(searchData.getUserName());
        List<RoleDO> roleInfoList=roleDAO.findAll();
        List<RolePermissionDO> rolePermissionInfoList=rolePermissionDAO.findByRoleId(userInfo.getRoleId());
        mav.addObject("userInfo",userInfo);
        mav.addObject("roleInfoList",roleInfoList);
        mav.addObject("rolePermissionInfoList",rolePermissionInfoList);
        return mav;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
        this.rolePermissionDAO = rolePermissionDAO;
    }
}
