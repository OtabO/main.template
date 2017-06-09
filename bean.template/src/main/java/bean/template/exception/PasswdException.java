package bean.template.exception;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class PasswdException extends Exception {
    public PasswdException(){
        super("密码错误,请重新输入");
    }
}
