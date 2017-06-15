package bo.template.impl;

import bean.template.*;
import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;
import bo.template.PermissionBO;
import dao.template.ModuleDAO;
import dao.template.RoleDAO;
import dao.template.RolePermissionDAO;
import dao.template.UserDAO;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class PermissionBOImpl implements PermissionBO {

    private UserDAO userDAO;

    private ModuleDAO moduleDAO;

    private RoleDAO roleDAO;

    private RolePermissionDAO rolePermissionDAO;

    public UserDO login(UserVO userVO) throws PasswdException, NonUserException {

        UserDO loginUser=userDAO.findByUserNameAndIsdelete(userVO.getUserName(),0);
        if(loginUser==null){
            throw new NonUserException();
        }
        UserDO permissionUser=userDAO.findByUserNameAndPwdMD5AndIsdelete(userVO.getUserName(),userVO.getPwdMD5(),0);
        if(permissionUser==null){
            throw new PasswdException();
        }
        return loginUser;
    }

    @Override
    public RoleDO findRole(UserDO userDO) {
        return roleDAO.findOne(userDO.getRoleId());
    }

    @Override
    public List<ModuleDO> findValidModules(UserDO userDO) {
        List<RolePermissionDO> rolePermissions=rolePermissionDAO.findByRoleId(userDO.getRoleId());
        List<ModuleDO> modules=new ArrayList<>();
        if(rolePermissions!=null){
            for(RolePermissionDO rolePermission:rolePermissions){
                modules.add(moduleDAO.findOne(rolePermission.getModuleId()));
            }
        }
        return modules;
    }

    @Override
    public boolean validateModule(UserDO userDO, String url) {
        List<ModuleDO> modules=findValidModules(userDO);
        for(ModuleDO module:modules){
            String urlPrefix=module.getUrlPrefix();
            if(!urlPrefix.startsWith("/")){
                urlPrefix+="/"+urlPrefix;
            }
            if(url.startsWith(urlPrefix)){
                return true;
            }
        }
        return false;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setModuleDAO(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }

    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void setRolePermissionDAO(RolePermissionDAO rolePermissionDAO) {
        this.rolePermissionDAO = rolePermissionDAO;
    }
}
