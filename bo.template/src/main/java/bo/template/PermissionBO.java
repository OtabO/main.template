package bo.template;
import bean.template.UserDO;
import bean.template.UserVO;
import bean.template.exception.NonUserException;
import bean.template.exception.PasswdException;

/**
 * Created by zhangsx on 2017/6/9.
 */
public interface PermissionBO {
    UserDO login(UserVO userVO) throws PasswdException ,NonUserException;
}
