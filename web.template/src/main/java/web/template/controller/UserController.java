package web.template.controller;

import bean.template.SearchDO;
import bean.template.UserDO;
import dao.template.UserDAO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zhangsx on 2017/6/15.
 */
@Controller()
@RequestMapping("/user")
public class UserController {

    private UserDAO userDAO;

    @RequestMapping(value = "/list.do")
    public ModelAndView list(SearchDO<UserDO> search) {
        if(search.getSearchData()==null){
            search.setSearchData(new UserDO());
        }
        if(search.getPage()==null){
            search.setPage(1);
        }
        if(search.getSize()==null){
            search.setSize(20);
        }
        UserDO searchData=search.getSearchData();
        Page<UserDO> page=userDAO.findAll(Example.of(searchData),new PageRequest(search.getPage(),search.getSize()));
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("page",page);
        mav.addObject("search");
        return mav;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
