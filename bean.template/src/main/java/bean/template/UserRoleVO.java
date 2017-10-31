package bean.template;

import java.util.List;

/**
 * Created by zhangsx on 2017/10/31.
 */
public class UserRoleVO {

    private UserDO userInfo;

    private RoleDO roleInfo;

    private List<RolePermissionDO> rolePermissionInfoList;

    public UserDO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDO userInfo) {
        this.userInfo = userInfo;
    }

    public RoleDO getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(RoleDO roleInfo) {
        this.roleInfo = roleInfo;
    }

    public List<RolePermissionDO> getRolePermissionInfoList() {
        return rolePermissionInfoList;
    }

    public void setRolePermissionInfoList(List<RolePermissionDO> rolePermissionInfoList) {
        this.rolePermissionInfoList = rolePermissionInfoList;
    }
}
