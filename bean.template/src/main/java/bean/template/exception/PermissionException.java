package bean.template.exception;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class PermissionException extends Exception {
    PermissionException(){
        super("未授权,请联系管理员");
    }
}
