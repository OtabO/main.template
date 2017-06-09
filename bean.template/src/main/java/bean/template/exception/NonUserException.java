package bean.template.exception;

/**
 * Created by zhangsx on 2017/6/9.
 */
public class NonUserException extends Exception {
    public NonUserException(){
        super("用户名不存在");
    }
}
