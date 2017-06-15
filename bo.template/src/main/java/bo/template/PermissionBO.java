package bo.template;
import bean.template.ModuleDO;
import bean.template.RoleDO;
import bean.template.UserDO;
import bean.template.UserVO;
import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;

import java.util.List;

/**
 * Created by zhangsx on 2017/6/9.
 */
public interface PermissionBO {
    UserDO login(UserVO userVO) throws PasswdException ,NonUserException;

    RoleDO findRole(UserDO userDO);

    List<ModuleDO> findValidModules(UserDO userDO);

    boolean validateModule(UserDO userDO,String url);
}
