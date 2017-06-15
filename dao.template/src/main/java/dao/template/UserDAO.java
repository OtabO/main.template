package dao.template;

import bean.template.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Created by zsx on 2017/6/14.
 */
public interface UserDAO extends JpaRepository<UserDO, Integer> {
    UserDO findByUserNameAndIsdelete(String userName,Integer isdelete);
    UserDO findByUserNameAndPwdMD5AndIsdelete(String userName,String pwdMD5,Integer isdelete);
}
