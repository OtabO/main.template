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
        UserDO userDO = new UserDO();
        userDO.setUserName(userVO.getUserName());
        userDO.setPwdMD5(userVO.getPwdMD5());
        return  userDAO.findOne(Example.of(userDO));
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
