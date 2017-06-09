package bean.template;

import java.io.Serializable;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class UserVO implements Serializable {

    private String userNameOrEmailOrPhoneNo;

    private String passwd;

    public String getUserNameOrEmailOrPhoneNo() {
        return userNameOrEmailOrPhoneNo;
    }

    public void setUserNameOrEmailOrPhoneNo(String userNameOrEmailOrPhoneNo) {
        this.userNameOrEmailOrPhoneNo = userNameOrEmailOrPhoneNo;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
