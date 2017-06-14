package bean.template;

import java.io.Serializable;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class UserVO implements Serializable {

    private String userName;

    private String pwdMD5;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwdMD5() {
        return pwdMD5;
    }

    public void setPwdMD5(String pwdMD5) {
        this.pwdMD5 = pwdMD5;
    }
}
