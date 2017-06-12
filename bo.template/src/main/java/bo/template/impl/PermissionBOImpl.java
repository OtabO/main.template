package bo.template.impl;

import bean.template.UserDO;
import bean.template.UserVO;
import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;
import bo.template.PermissionBO;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class PermissionBOImpl implements PermissionBO {
    public UserDO login(UserVO userVO) throws PasswdException ,NonUserException{
        UserDO userDO=new UserDO();
        userDO.setUserName("test");
        return userDO;
    }
}
