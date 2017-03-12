package org.generaltune.web.user;

import org.generaltune.entity.Seckill;
import org.generaltune.entity.User;
import org.generaltune.service.UserService;
import org.generaltune.web.base.DefaultAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by zhumin on 2017/3/11.
 */
@Controller //@Service @Component
@RequestMapping("/")  //url:/模块/资源/{id}/细分   /seckill/list
public class LoginAction extends DefaultAction {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private UserService userService;


    /**
     * 登录打开页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public  String login(Model model) {
        //获取列表页
//        List<Seckill> list = seckillService.getSeckillList(0, 10);
//        model.addAttribute("list", list);
        //list.jsp + model = ModelAndView
        return "login";
    }

    /**
     * 登录提交页面
     * @param username
     * @param password
     * @param url
     * @param remember
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void authenticate(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "url", required = false) String url,
            @RequestParam(value = "remember", required = false) String remember
    ) {
        logger.info(username,password,url,remember);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String signup() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
        produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void register(
        @RequestParam(value = "username", required = true) String username,
        @RequestParam(value = "name", required = true) String name,
        @RequestParam(value = "password", required = true) String password,
        @RequestParam(value = "email", required = true) String email,
        @RequestParam(value = "description", required = true) String description,
        @RequestParam(value = "phone", required = true) Long phone
    ) {
        Calendar calendar= Calendar.getInstance();
        calendar.set(1988,12,12);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setBirthday(calendar.getTime());
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setDescripiton(description);
        user.setRegion((short) 1);
        user.setStatus(2);
        user.setType("武将");
        user.setVersion(1111111l);

        logger.info(username +"=>"+ password +"=>"+  email+"=>"+ phone +"=>"+  "打印提交信息！");
        logger.error(username +"=>"+  password+"=>"+ email +"=>"+  phone+"=>"+  "打印提交信息！");
        userService.addUser(user);
    }



}
