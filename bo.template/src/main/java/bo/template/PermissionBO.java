package bo.template;
import bean.template.UserVO;
import bean.template.exception.PasswdException;

/**
 * Created by zhangsx on 2017/6/9.
 */
public interface PermissionBO {
    void login(UserVO userVO) throws PasswdException;
}
