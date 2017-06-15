package dao.template;

import bean.template.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangsx on 2017/6/15.
 */
public interface RoleDAO extends JpaRepository<RoleDO, Integer> {
}
