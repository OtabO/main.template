package dao.template;

import bean.template.ModuleDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhangsx on 2017/6/15.
 */
public interface ModuleDAO extends JpaRepository<ModuleDO, Integer> {

}
