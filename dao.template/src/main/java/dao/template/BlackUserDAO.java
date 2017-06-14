package dao.template;

import bean.template.BlackUserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

/**
 * Created by zhangsx on 2017/6/14.
 */
public interface BlackUserDAO extends JpaRepository<BlackUserDO, Integer> {
//    BlackUserDO getByMemberId(String memberId);
}
