package web.template.support;

import bean.template.SearchDO;
import bean.template.UserDO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Created by zhangsx on 2017/6/16.
 */
public class SearchConverter implements Converter<UserDO,UserDO> {

    @Override
    public UserDO convert(UserDO source) {
        return null;
    }
}
