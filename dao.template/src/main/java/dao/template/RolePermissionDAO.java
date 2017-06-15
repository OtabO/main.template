package dao.template;

import bean.template.RolePermissionDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangsx on 2017/6/15.
 */
public interface RolePermissionDAO extends JpaRepository<RolePermissionDO, Integer> {
    List<RolePermissionDO> findByRoleId(Integer roleId);
}
