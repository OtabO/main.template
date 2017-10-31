package web.template.controller;

import bean.template.SearchDO;
import bean.template.UserDO;
import bean.template.UserRoleVO;
import dao.template.UserDAO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by zhangsx on 2017/6/15.
 */
@Controller()
@RequestMapping("/user")
public class UserController {

    private UserDAO userDAO;

    @RequestMapping(value = "/list.do")
    public ModelAndView list(UserDO searchData) {
        Page<UserDO> pageResult=userDAO.findAll(Example.of(searchData),new PageRequest(0,20));
        ModelAndView mav = new ModelAndView("user");
        mav.addObject("page",pageResult);
        mav.addObject("search");
        return mav;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
