package bo.template.impl;

import bean.template.UserDO;
import bean.template.UserVO;
import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;
import bo.template.PermissionBO;
import dao.template.UserDAO;
import org.springframework.data.domain.Example;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class PermissionBOImpl implements PermissionBO {

    private UserDAO userDAO;

    public UserDO login(UserVO userVO) throws PasswdException, NonUserException {

        UserDO loginUser=userDAO.findByUserName(userVO.getUserName());
        if(loginUser==null){
            throw new NonUserException();
        }
        UserDO permissionUser=userDAO.findByUserNameAndPwdMD5(userVO.getUserName(),userVO.getPwdMD5());
        if(permissionUser==null){
            throw new PasswdException();
        }
        return loginUser;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
